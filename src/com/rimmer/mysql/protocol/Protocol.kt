package com.rimmer.mysql.protocol

import com.rimmer.mysql.protocol.constants.CharSet
import com.rimmer.mysql.protocol.constants.ResultMarker
import com.rimmer.mysql.protocol.decoder.*
import com.rimmer.mysql.protocol.encoder.*
import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufUtil
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.util.concurrent.DefaultPromise
import io.netty.util.concurrent.Future
import io.netty.util.concurrent.Promise
import java.util.*
import java.util.concurrent.atomic.AtomicReference

/** Handles decoding packets sent from MySQL. */
class ProtocolHandler(
    val user: String,
    val password: String,
    val database: String,
    val connectPromise: Promise<Connection>
): SimpleChannelInboundHandler<ByteBuf>(), Connection {
    private var currentContext: ChannelHandlerContext? = null
    private var lastException: Exception? = null
    private var queryPromiseRef = AtomicReference<Promise<QueryResult>>(null)
    private var preparePromiseRef = AtomicReference<Promise<Statement>>(null)

    private var hasHandshake = false
    private var insideQuery = false
    private var insidePrepare = false

    /** Used to handle prepared statement param definitions. */
    private var processingParams = false
    private var totalParamCount = 0
    private var processedParamCount = 0

    /** Used to handle prepared statement and query result column definitions. */
    private var processingColumns = false
    private var totalColumnCount = 0
    private var processedColumnCount = 0

    /** Stores the types of columns we will receive. This is resized when needed. */
    private var columnTypes = ShortArray(32)

    /** Stores the names of columns we will receive. This is resized when needed. */
    private var columnNames = Array(32) {""}

    /** The requested column types for the currently running query. This array is produced externally. */
    private var requestedTypes: List<Class<*>>? = null

    /** The id of the statement we are preparing. */
    private var prepareId = 0

    /** The column count of a query result has been read. */
    private var hasReadColumnCount = false

    /** The query column definitions have been read, we are now reading result rows. */
    private var processingRows = false

    /** The result of the current query that is being built. */
    private var result = ArrayList<Row>()

    /** The server capability bitmap we received. */
    private var serverCaps = 0

    /** The buffer we use to accumulate partial packet data. */
    private var accumulator: ByteBuf? = null

    private var queryPromise: Promise<QueryResult>?
        get() = queryPromiseRef.get()
        set(v) {
            if(!queryPromiseRef.compareAndSet(null, v)) {
                throw SqlException("Connection still running query")
            }
        }

    private var preparePromise: Promise<Statement>?
        get() = preparePromiseRef.get()
        set(v) {
            if(!preparePromiseRef.compareAndSet(null, v)) {
                throw SqlException("Connection still running prepare")
            }
        }

    override fun prepare(query: String): Future<Statement> {
        assertReadyForQuery()
        val promise = DefaultPromise<Statement>(currentContext!!.executor())
        preparePromise = promise
        currentContext!!.writeAndFlush(writePrepareStatement(query))
        insidePrepare = true
        return promise
    }

    override fun query(statement: Statement, values: List<Any>, targetTypes: List<Class<*>>?): Future<QueryResult> {
        assertReadyForQuery()
        requestedTypes = targetTypes
        val promise = DefaultPromise<QueryResult>(currentContext!!.executor())
        queryPromise = promise
        currentContext!!.writeAndFlush(writeQuery(statement.statementId, values))
        insideQuery = true
        return promise
    }

    override fun disconnect() {
        if(hasHandshake) {
            failQuery(SqlException("Connection is being closed"))
            clearState()
            currentContext?.writeAndFlush(writeQuit())?.then {
                currentContext!!.close()
            }
        }
    }

    /**
     * Called when the client receives a handshake packet after connecting to the server.
     * This doesn't mean that we have a successful connection - we have to authenticate first.
     */
    private fun onHandshake(packet: ByteBuf) = readHandshake(packet) {
        version, connection, capabilities, charSet, statusFlags, authSecret, authMethod ->

        // Reply with the authentication.
        println("Received handshake")
        serverCaps = capabilities
        currentContext!!.writeAndFlush(writeAuthentication(
            maxPacketSize = 0x00ffffff,
            charset = CharSet.UTF8,
            user = user,
            password = password,
            seed = authSecret,
            database = database
        ))
    }

    /**
     * Called with an Ok packet after we successfully authenticated with the server.
     * After this we can start using the connection normally.
     */
    private fun onAuthenticate(packet: ByteBuf) = readOk(packet, serverCaps) {
        affectedRows, lastInsertId, serverStatus, warnings, message ->

        hasHandshake = true
        connectPromise.setSuccess(this)
    }

    /**
     * Called when we receive an error from the server.
     * This can happen in any context.
     */
    private fun onError(packet: ByteBuf) = readError(packet, serverCaps) {
        errorCode, sqlState, message ->

        println("Received error: $message")
        onException(SqlException(message))
    }

    private fun onException(exception: Exception) {
        lastException = exception
        connectPromise.tryFailure(exception)

        if(insideQuery) {
            failQuery(exception)
        } else if(insidePrepare) {
            failPrepare(exception)
        }

        clearState()
    }

    /**
     * Called when we receive a result row.
     * The row is decoded and added to the result we are building.
     */
    fun onRow(packet: ByteBuf) {
        val row = Array<Any?>(totalColumnCount) {null}
        readResultRow(packet, totalColumnCount, columnTypes, requestedTypes) {
            column, value -> row[column] = value
        }

        val index = result.size
        result.add(object: Row {
            override val rowIndex: Int get() = index
            override fun get(index: Int) = row[index]
            override fun iterator() = row.iterator()
        })
    }

    /**
     * Called whenever we receive a column definition.
     *  - If reading params, it is sent to the prepared statement params.
     *  - If reading columns, it is sent to the prepared statement or query columns.
     */
    private fun onColumnDefinition(index: Int, packet: ByteBuf) = readColumnDefinition(packet) {
        catalog, database, table, originalTable, name, originalName, charSet, columnLength, columnType, flags, decimals ->

        // We currently only use the columns for query results.
        if(insideQuery) {
            // Make sure we have enough space.
            if(totalColumnCount > columnTypes.size) {
                columnTypes = ShortArray(totalColumnCount)
            }

            if(totalColumnCount > columnNames.size) {
                columnNames = Array(totalColumnCount) {""}
            }

            columnTypes[index] = columnType
            columnNames[index] = name
        }
    }

    /**
     * Called after we have finished reading column definitions.
     * This happens when preparing a statement (for queries, columns are handled a different way).
     */
    private fun onColumnsFinished(packet: ByteBuf) = readEOF(packet, serverCaps) {
        warnings, serverStatus ->

        processingColumns = false
        if(insidePrepare) {
            // A prepare is finished after the columns have been read.
            succeedPrepare()
        } else if(insideQuery) {
            processingRows = true
        } else {
            throw SqlException("onColumnsFinished called with invalid internal state")
        }
    }

    /**
     * Called after we have finished reading column definitions.
     * This happens when preparing a statement.
     */
    private fun onParamsFinished(packet: ByteBuf) = readEOF(packet, serverCaps) {
        warnings, serverStatus ->

        if(!insidePrepare || !processingParams) {
            throw SqlException("onParamsFinished called with invalid internal state")
        }

        processingParams = false

        // If there are columns for the statement, we read them after this.
        if(totalColumnCount > 0) {
            processingColumns = true
        } else {
            // Otherwise, the prepare is finished.
            succeedPrepare()
        }
    }

    /**
     * Called after we have finished reading rows for a query result.
     * The rows are followed by and EOF packet.
     */
    private fun onRowsFinished(packet: ByteBuf) = readEOF(packet, serverCaps) {
        warnings, serverStatus ->

        if(!processingRows) {
            throw SqlException("onRowsFinished called with invalid internal state")
        }

        processingRows = false
        succeedQuery(0, 0, "")
    }

    /**
     * Called after the server has created a prepared statement.
     * This may be followed by param and column definitions,
     * which have to be read before finishing the statement.
     */
    private fun onPrepareOk(packet: ByteBuf) = readPrepareStatement(packet) {
        statementId: Int, columnCount: Int, paramCount: Int, warningCount: Int ->

        processingParams = true
        processingColumns = true
        totalColumnCount = columnCount
        totalParamCount = paramCount
        prepareId = statementId

        if(columnCount == 0 && paramCount == 0) {
            succeedPrepare()
        }
    }

    /**
     * Called after the server has finished executing a query without results.
     * The packet contains affected rows, insert id, etc.
     */
    private fun onQueryOk(packet: ByteBuf) = readOk(packet, serverCaps) {
        affectedRows, lastInsertId, serverStatus, warnings, message ->
        succeedQuery(affectedRows, lastInsertId, message)
    }





    private fun clearPreparePromise() = preparePromiseRef.getAndSet(null)

    /** Fails a prepare-command and clears state. */
    private fun failPrepare(cause: Throwable?) {
        clearState()
        clearPreparePromise()?.tryFailure(cause)
    }

    /** Finishes a prepare-command with buffered data and clears state. */
    private fun succeedPrepare() {
        val columns = totalColumnCount
        val params = totalParamCount
        val id = prepareId

        clearState()
        clearPreparePromise()?.setSuccess(Statement(id, columns, params))
    }

    private fun clearQueryPromise() = queryPromiseRef.getAndSet(null)

    /** Fails a query-command and clears state. */
    private fun failQuery(cause: Throwable?) {
        clearState()
        clearQueryPromise()?.tryFailure(cause)
    }

    /** Finishes a query-command with buffered results and clears state. */
    private fun succeedQuery(affectedRows: Long, lastInsertId: Long, message: String) {
        println("Finished query with no results")
        val queryResult = if(totalColumnCount > 0) {
            val names = Array(totalColumnCount) { columnNames[it] }
            val values = Array(result.size) { result[it] }
            ResultSet(values, names)
        } else null

        val result = QueryResult(affectedRows, lastInsertId, message, queryResult)
        clearState()
        clearQueryPromise()?.setSuccess(result)
    }

    /** Makes sure the connection isn't busy. */
    private fun assertReadyForQuery() {
        if(insideQuery || insidePrepare) throw SqlException("Connection still running query")
    }







    override fun channelRead0(context: ChannelHandlerContext, source: ByteBuf) {
        currentContext = context
        val packet = if(accumulator == null) {
            source
        } else {
            Unpooled.wrappedBuffer(accumulator, source)
        }

        // Decode packets until the buffer doesn't contain any more full ones.
        while(readPacket(packet)) {}

        // If there is a partial packet left, we save it until more data is received.
        if(packet.readableBytes() > 0) {
            accumulator = Unpooled.buffer(packet.readableBytes())
            accumulator!!.writeBytes(packet)
        } else {
            accumulator = null
        }
    }

    private fun readPacket(source: ByteBuf): Boolean {
        if(source.readableBytes() <= 4) return false
        source.markReaderIndex()

        // Each packet starts with a 24-bit packet length and an 8-bit sequence index.
        val length = ByteBufUtil.swapMedium(source.readUnsignedMedium())
        source.readUnsignedByte() // Sequence index, we don't use this for anything.

        // Make sure the buffer contains the full packet.
        if(source.readableBytes() < length) {
            source.resetReaderIndex()
            return false
        }

        // After the length each packet contains an 8-bit type marker.
        val type = source.getByte(source.readerIndex())

        // Handle the packet we received.
        if(hasHandshake) {
            handleResponse(type, source)
        } else {
            handleHandshake(type, source)
        }

        return true
    }

    private fun handleHandshake(type: Byte, source: ByteBuf) {
        when(type) {
            ResultMarker.error -> onError(source)
            ResultMarker.ok -> onAuthenticate(source)
            else -> onHandshake(source)
        }
    }

    private fun handleResponse(type: Byte, source: ByteBuf) {
        when(type) {
            ResultMarker.error -> onError(source)

            ResultMarker.eof -> if(processingParams && totalParamCount > 0) {
                onParamsFinished(source)
            } else if(processingColumns) {
                onColumnsFinished(source)
            } else if(processingRows) {
                onRowsFinished(source)
            } else {
                onException(SqlException("Received an unrecognized EOF packet"))
            }

            ResultMarker.ok -> if(insidePrepare) {
                onPrepareOk(source)
            } else if(processingRows) {
                handleQueryResponse(source)
            } else if(insideQuery) {
                onQueryOk(source)
            } else {
                onException(SqlException("Received an unrecognized OK packet"))
            }

            else -> if(insideQuery || insidePrepare) {
                handleQueryResponse(source)
            } else {
                onException(SqlException("Received an unknown packet type"))
            }
        }
    }

    private fun handleQueryResponse(source: ByteBuf) {
        if(processingParams && totalParamCount != processedParamCount) {
            onColumnDefinition(processedParamCount, source)
            processedParamCount++
        } else if(processingColumns && totalColumnCount != processedColumnCount) {
            onColumnDefinition(processedColumnCount, source)
            processedColumnCount++
        } else if(insideQuery) {
            if(hasReadColumnCount) {
                onRow(source)
            } else {
                hasReadColumnCount = true
                processingColumns = true
                totalColumnCount = source.readLengthEncoded().toInt()
            }
        } else {
            // This happens when the result of a text query is returned.
            // We only support the binary interface, so we don't handle this.
            throw SqlException("MySQL text protocol is unsupported")
        }
    }

    private fun clearState() {
        insideQuery = false
        insidePrepare = false
        processingParams = false
        processingColumns = false
        totalColumnCount = 0
        totalParamCount = 0
        processedColumnCount = 0
        processedParamCount = 0
        hasReadColumnCount = false
        processingRows = false
        requestedTypes = null
        result.clear()
    }
}