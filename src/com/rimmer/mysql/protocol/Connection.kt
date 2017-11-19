package com.rimmer.mysql.protocol

import com.rimmer.mysql.protocol.decoder.unknownTarget
import io.netty.bootstrap.Bootstrap
import io.netty.buffer.ByteBuf
import io.netty.buffer.PooledByteBufAllocator
import io.netty.channel.*
import io.netty.channel.epoll.Epoll
import io.netty.channel.epoll.EpollSocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.util.concurrent.*
import java.util.*

/** Represents a single serial connection to the database. */
interface Connection {
    /**
     * Performs a database query.
     * @param query The query to perform, in prepared statement format.
     * @param values The parameters to send to the query.
     * @param targetTypes The types you want the query to return. If not set, the driver decides what types to return.
     * @param data Data for the query listener that will be associated with the query.
     * @return A query result object.
     */
    fun query(query: String, values: List<Any?>, targetTypes: List<Class<*>>?, data: Any? = null, f: (QueryResult?, Throwable?) -> Unit)

    /** Closes this connection. */
    fun disconnect()

    /**
     * Set if the connection is currently connected to a database.
     * This does not guarantee that the connection is still alive.
     */
    val connected: Boolean

    /** Set if the connection is currently performing a command. */
    val busy: Boolean

    /** The amount of time this connection has been querying since the query start. If idle, this returns 0. */
    val busyTime: Long

    /** The amount of time this connection has been idle since the last action. */
    val idleTime: Long
}

/** Represents a listener to executed queries which will be called for each query. */
interface QueryListener {
    fun onQuery(data: Any?, query: String, result: QueryResult?, error: Throwable?)
}

/** A codec helper that can be provided to support encoding and decoding custom types. */
interface CodecExtender {
    fun encode(buffer: ByteBuf, types: ByteArray, index: Int, value: Any) {}
    fun decodeDecimal(buffer: ByteBuf, targetType: Class<*>?): Any = throw unknownTarget(targetType)
    fun decodeByte(buffer: ByteBuf, targetType: Class<*>?): Any = throw unknownTarget(targetType)
    fun decodeShort(buffer: ByteBuf, targetType: Class<*>?): Any = throw unknownTarget(targetType)
    fun decodeInt(buffer: ByteBuf, targetType: Class<*>?): Any = throw unknownTarget(targetType)
    fun decodeFloat(buffer: ByteBuf, targetType: Class<*>?): Any = throw unknownTarget(targetType)
    fun decodeDouble(buffer: ByteBuf, targetType: Class<*>?): Any = throw unknownTarget(targetType)
    fun decodeDate(buffer: ByteBuf, targetType: Class<*>?): Any = throw unknownTarget(targetType)
    fun decodeLong(buffer: ByteBuf, targetType: Class<*>?): Any = throw unknownTarget(targetType)
    fun decodeString(buffer: ByteBuf, targetType: Class<*>?): Any = throw unknownTarget(targetType)
    fun decodeBit(buffer: ByteBuf, targetType: Class<*>?): Any = throw unknownTarget(targetType)
}

/** Contains the result data for a single row. */
interface Row: Sequence<Any?> {
    val rowIndex: Int
    operator fun get(index: Int): Any?
}

/** Contains the result data for a single query. */
class ResultSet(val data: Array<Row>, val columnNames: Array<String>) {
    /** Returns a list of rows, where each row contains a key -> value map. */
    val map: List<Map<String, Any?>> get() {
        return data.map { row ->
            val map = HashMap<String, Any?>()
            columnNames.forEachIndexed { i, s ->
                map[s] = row[i]
            }
            map
        }
    }
}

/**
 * Contains the result of a query.
 * @param affectedRows The number of rows that was changed or inserted.
 * @param lastInsert The id of the last item that was inserted by this query.
 * @param status A server status message.
 * @param elapsed The amount of time in ns this query took.
 * @param result The result data of this query, if any.
 */
class QueryResult(val affectedRows: Long, val lastInsert: Long, val status: String, val elapsed: Long, val result: ResultSet?)

/**
 * Connects to a database.
 * @param group The event loop group to run the client on.
 * @param host The database host to connect to.
 * @param port The port to use for connecting.
 * @param user The username to connect with.
 * @param password The password for this user.
 * @param database The database to connect to.
 * @param listener Called when queries happen.
 * @param useNative Use native transport instead of NIO (Linux only).
 */
fun connect(
    group: EventLoopGroup,
    host: String,
    port: Int,
    user: String,
    password: String,
    database: String,
    listener: QueryListener? = null,
    useNative: Boolean = false,
    codec: CodecExtender? = null,
    debugStatementsAbove: Int = 0,
    f: (Connection?, Throwable?) -> Unit
) {
    val channelType = if(useNative && Epoll.isAvailable()) EpollSocketChannel::class.java else NioSocketChannel::class.java

    // Create the connection channel.
    val bootstrap = Bootstrap()
        .option(ChannelOption.SO_KEEPALIVE, true)
        .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
        .group(group)
        .channel(channelType)
        .handler(object: ChannelInitializer<Channel>() {
            override fun initChannel(channel: Channel) {
                channel.pipeline().addLast(ProtocolHandler(user, password, database, f, listener, codec, debugStatementsAbove))
            }
        })

    // Try to connect to the database.
    bootstrap.connect(host, port) onFail { f(null, it) }
}

class SqlException(val code: Int, val state: String, cause: String): Exception(cause)

inline infix fun <T> Future<T>.then(crossinline f: (T) -> Unit) {
    addListener(GenericFutureListener<Future<T>> { future ->
        if(future.isSuccess) {
            f(future.get())
        }
    })
}

inline infix fun <T> Future<T>.onFail(crossinline f: (Exception) -> Unit) {
    addListener { future ->
        val cause = future.cause()
        if(cause != null && cause is Exception) f(cause)
    }
}