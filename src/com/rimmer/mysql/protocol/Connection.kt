package com.rimmer.mysql.protocol

import io.netty.bootstrap.Bootstrap
import io.netty.buffer.PooledByteBufAllocator
import io.netty.channel.*
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.util.concurrent.*

inline infix fun <T> Future<T>.then(crossinline f: (T) -> Unit) {
    addListener(GenericFutureListener<Future<T>> { future ->
        f(future.get())
    })
}

inline infix fun <T> Future<T>.onFail(crossinline f: (Exception) -> Unit) {
    addListener(GenericFutureListener<Future<T>> { future ->
        val cause = future.cause()
        if (cause != null && cause is Exception) f(cause)
    })
}

interface Row: Sequence<Any?> {
    val rowIndex: Int
    fun get(index: Int): Any?
}

class ResultSet(val data: Array<Row>, val columnNames: Array<String>)
class QueryResult(val affectedRows: Long, val lastInsert: Long, val status: String, val result: ResultSet?)

class SqlException(cause: String): Exception(cause)

class Statement(val statementId: Int, val columnCount: Int, val paramCount: Int)

interface Connection {
    fun prepare(query: String): Future<Statement>
    fun query(statement: Statement, values: List<Any>, targetTypes: List<Class<*>>?): Future<QueryResult>
    fun disconnect()
}

fun connect(group: EventLoopGroup, host: String, port: Int, user: String, password: String, database: String): Future<Connection> {
    val connection = DefaultPromise<Connection>(GlobalEventExecutor.INSTANCE)

    // Create the connection channel.
    val bootstrap = Bootstrap()
        .option(ChannelOption.SO_KEEPALIVE, true)
        .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
        .group(group)
        .channel(NioSocketChannel::class.java)
        .handler(object: ChannelInitializer<Channel>() {
            override fun initChannel(channel: Channel) {
                channel.pipeline().addLast(ProtocolHandler(user, password, database, connection))
            }
        })

    // Try to connect to the database.
    bootstrap.connect(host, port) onFail {
        connection.setFailure(it)
    }

    return connection
}