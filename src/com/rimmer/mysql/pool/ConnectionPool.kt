package com.rimmer.mysql.pool

import com.rimmer.mysql.protocol.Connection
import com.rimmer.mysql.protocol.SqlException
import com.rimmer.mysql.protocol.then
import io.netty.util.concurrent.DefaultPromise
import io.netty.util.concurrent.EventExecutor
import io.netty.util.concurrent.Future
import io.netty.util.concurrent.Promise
import java.util.*

/**
 * @param maxItems The maximum number of active connections in the pool.
 * @param maxIdleTime The maximum idle time in ns for a connection. After this time it is destroyed.
 * @param maxBusyTime The maximum busy time in ns for a connection. After it has been busy for this time, it is destroyed.
 * @param maxWaiting The maximum number of users that can be waiting for a connection.
 * @param keepaliveInterval The interval in ns between which connections are tested.
 */
class PoolConfiguration(
    val maxItems: Int,
    val maxIdleTime: Long = 10L * 1000L * 1000000L,
    val maxBusyTime: Long = 0,
    val maxWaiting: Int = 128,
    val keepaliveInterval: Long = 30L * 1000L * 1000000L
)

/** Provides an interface for using connections from a pool. */
interface ConnectionPool {
    /** Returns a pooled connection ready for use. */
    fun get(): Future<Connection>

    /** Releases a pooled connection. This is also done by Connection.disconnect() */
    fun release(connection: Connection)
}

/** Implements a connection pool meant for use from a single thread. */
class SingleThreadPool(
    val config: PoolConfiguration,
    val executor: EventExecutor,
    val creator: () -> Future<Connection>
): ConnectionPool {
    private val timer = Timer("connection-pool-keepalive")
    private val idlePool = Stack<PoolConnection>()
    private val connections = ArrayList<PoolConnection>()
    private val waiting: Queue<Promise<Connection>> = LinkedList<Promise<Connection>>()
    private var connectionCount = 0

    init {
        val interval = config.keepaliveInterval / 1000000L
        timer.scheduleAtFixedRate(object: TimerTask() {
            override fun run() = executor.execute {
                checkConnections()
            }
        }, interval, interval)
    }

    override fun get(): Future<Connection> {
        if(idlePool.empty()) {
            val connection = DefaultPromise<Connection>(executor)
            if(connectionCount < config.maxItems) {
                connectionCount++
                creator().then {
                    val pooled = PoolConnection(it, this)
                    connections.add(pooled)
                    connection.setSuccess(pooled)
                }
            } else if(waiting.size < config.maxWaiting) {
                waiting.offer(connection)
            } else {
                connection.setFailure(SqlException("Connection queue full"))
            }

            return connection
        } else {
            val connection = idlePool.pop()

            // Check if the connection is busy, just in case we are currently doing an alive-test.
            if(connection.busy) {
                return get()
            } else {
                val promise = DefaultPromise<Connection>(executor)
                promise.setSuccess(connection)
                return promise
            }
        }
    }

    override fun release(connection: Connection) {
        if(connection is PoolConnection) {
            if(waiting.size > 0) {
                waiting.poll().trySuccess(connection)
            } else {
                idlePool.add(connection)
            }
        }
    }

    fun checkConnections() {
        var i = 0
        while(i < connections.size) {
            val c = connections[i]
            val busy = config.maxBusyTime > 0 && c.busyTime > config.maxBusyTime
            val idle = config.maxIdleTime > 0 && c.idleTime > config.maxIdleTime
            if(busy || idle) {
                connectionCount--
                c.connection.disconnect()
                connections.removeAt(i)
                println("Closing idle connection $i")
            } else {
                i++
            }

            // TODO: Do a keep-alive test here without causing synchronization issues.
        }
    }
}

/** Wraps a connection managed by a pool. */
class PoolConnection(val connection: Connection, val pool: ConnectionPool): Connection by connection {
    override fun disconnect() {
        pool.release(this)
    }
}
