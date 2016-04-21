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
 * @param checkDelta The minimum time between checks for stuck connections.
 */
class PoolConfiguration(
    val maxItems: Int,
    val maxIdleTime: Long = 60L * 1000L * 1000000L,
    val maxBusyTime: Long = 0,
    val maxWaiting: Int = 128,
    val checkDelta: Long = 60L * 1000L * 1000000L
)

/** Provides an interface for using connections from a pool. */
interface ConnectionPool {
    /** Returns a pooled connection ready for use. */
    fun get(f: (Connection?, Throwable?) -> Unit)

    /** Releases a pooled connection. This is also done by Connection.disconnect() */
    fun release(connection: Connection)
}

/** Implements a connection pool meant for use from a single thread. */
class SingleThreadPool(
    val config: PoolConfiguration,
    val creator: ((Connection?, Throwable?) -> Unit) -> Unit
): ConnectionPool {
    private val idlePool = Stack<PoolConnection>()
    private val connections = ArrayList<PoolConnection>()
    private val waiting: Queue<(Connection?, Throwable?) -> Unit> = LinkedList()
    private var connectionCount = 0
    private var lastCheck = System.nanoTime()

    override fun get(f: (Connection?, Throwable?) -> Unit) {
        if(idlePool.empty()) {
            if(connectionCount < config.maxItems) {
                connectionCount++
                creator {c, e ->
                    if(c == null) {
                        f(null, e)
                    } else {
                        val pooled = PoolConnection(c, this)
                        connections.add(pooled)
                        f(pooled, null)
                    }
                }
            } else {
                // There being no connections available may indicate a high load,
                // but may also indicate connections being stuck.
                // We check each one for query timeouts.
                val time = System.nanoTime()
                if(time - lastCheck > config.checkDelta) {
                    checkConnections()
                    lastCheck = time
                    get(f)
                } else if(waiting.size < config.maxWaiting) {
                    // Queue this request if we have space left.
                    waiting.offer(f)
                } else {
                    f(null, SqlException(0, "", "Connection queue full"))
                }
            }
        } else {
            val connection = idlePool.pop()

            // If the connection timed out or is otherwise unusable, we drop it and create a new one.
            if(connection.busy || !connection.connected || connection.idleTime > config.maxIdleTime) {
                connection.connection.disconnect()
                connectionCount--
                get(f)
            } else {
                f(connection, null)
            }
        }
    }

    override fun release(connection: Connection) {
        if(connection is PoolConnection) {
            // If anyone is currently waiting for a connection, we directly call it.
            // Otherwise we return the connection to the pool.
            val waiter = waiting.poll()
            if(waiter == null) {
                idlePool.add(connection)
            } else {
                waiter(connection, null)
            }
        }
    }

    fun checkConnections() {
        println("Checking MySQL connections...")

        var i = 0
        while(i < connections.size) {
            val c = connections[i]
            val busy = config.maxBusyTime > 0 && c.busyTime > config.maxBusyTime
            val idle = config.maxIdleTime > 0 && c.idleTime > config.maxIdleTime
            if(busy || idle) {
                connectionCount--
                c.connection.disconnect()
                connections.removeAt(i)
                println("Closing idle MySQL connection $i")
            } else {
                i++
            }
        }
    }
}

/** Wraps a connection managed by a pool. */
class PoolConnection(val connection: Connection, val pool: ConnectionPool): Connection by connection {
    override fun disconnect() {
        pool.release(this)
    }
}
