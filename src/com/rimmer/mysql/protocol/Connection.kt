package com.rimmer.mysql.protocol

import io.netty.bootstrap.Bootstrap
import io.netty.buffer.PooledByteBufAllocator
import io.netty.channel.*
import io.netty.channel.epoll.EpollEventLoopGroup
import io.netty.channel.epoll.EpollSocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.util.concurrent.*

/** Represents a single serial connection to the database. */
interface Connection {
    /**
     * Performs a database query.
     * @param query The query to perform, in prepared statement format.
     * @param values The parameters to send to the query.
     * @param targetTypes The types you want the query to return. If not set, the driver decides what types to return.
     * @return A query result object.
     */
    fun query(query: String, values: List<Any?>, targetTypes: List<Class<*>>?, f: (QueryResult?, Throwable?) -> Unit)

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

/** Contains the result data for a single row. */
interface Row: Sequence<Any?> {
    val rowIndex: Int
    fun get(index: Int): Any?
}

/** Contains the result data for a single query. */
class ResultSet(val data: Array<Row>, val columnNames: Array<String>)

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
 */
fun connect(group: EventLoopGroup, host: String, port: Int, user: String, password: String, database: String, f: (Connection?, Throwable?) -> Unit) {
    val channelType = if(group is EpollEventLoopGroup) EpollSocketChannel::class.java else NioSocketChannel::class.java

    // Create the connection channel.
    val bootstrap = Bootstrap()
        .option(ChannelOption.SO_KEEPALIVE, true)
        .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
        .group(group)
        .channel(channelType)
        .handler(object: ChannelInitializer<Channel>() {
            override fun initChannel(channel: Channel) {
                channel.pipeline().addLast(ProtocolHandler(user, password, database, f))
            }
        })

    // Try to connect to the database.
    bootstrap.connect(host, port) onFail { f(null, it) }
}

class SqlException(cause: String): Exception(cause)

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