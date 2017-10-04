package com.rimmer.mysql.dsl

import com.rimmer.mysql.protocol.Connection
import com.rimmer.mysql.protocol.QueryResult
import java.util.*

class Insert(val table: Table, val isIgnore: Boolean = false, val isReplace: Boolean = false): Query {
    val values = LinkedHashMap<Column<*>, Any?>()

    operator fun <T> set(column: Column<T>, value: T) {
        values.put(column, value)
    }

    override fun run(c: Connection, listenerData: Any?, f: (QueryResult?, Throwable?) -> Unit) {
        val builder = QueryBuilder()

        builder.append("INSERT ")
        if(isIgnore) { builder.append("IGNORE ") }
        builder.append("INTO ")
        table.format(builder)

        builder.append(" (")
        sepBy(values.iterator(), builder.string, ", ") {it.key.format(builder)}
        builder.append(") VALUES (")
        sepBy(values.iterator(), builder.string, ", ") {builder.argument(it.value)}
        builder.append(") ")

        if(isReplace) {
            builder.append("ON DUPLICATE KEY UPDATE ")
            sepBy(values.iterator(), builder.string, ", ") {
                it.key.format(builder)
                builder.append("=VALUES(")
                it.key.format(builder)
                builder.append(')')
            }
        }

        builder.run(c, null, listenerData, f)
    }
}

class BatchInsert(val table: Table, val isIgnore: Boolean = false, val isReplace: Boolean = false): Query {
    val values = ArrayList<ArrayList<Any?>>()
    val columns = ArrayList<Column<*>>()

    fun addBatch() {
        if(values.size > 0 && values.last().size != columns.size) {
            throw IllegalArgumentException("Every inserted batch must have the same number of columns.")
        }

        values.add(ArrayList())
    }

    operator fun <T> set(column: Column<T>, value: T) {
        val batch = values.last()
        if(values.size > 1) {
            if(columns.size <= batch.size || columns[batch.size] !== column) {
                throw IllegalArgumentException("Every inserted batch must contain the same columns.")
            }
        } else {
            columns.add(column)
        }

        batch.add(value)
    }

    override fun run(c: Connection, listenerData: Any?, f: (QueryResult?, Throwable?) -> Unit) {
        if(values.isEmpty()) {
            f(QueryResult(0, 0, "", 0, null), null)
            return
        }

        val builder = QueryBuilder()
        builder.append("INSERT ")
        if(isIgnore) { builder.append("IGNORE ") }

        builder.append("INTO ")
        table.format(builder)
        builder.append(" (")
        sepBy(columns.iterator(), builder.string, ", ") { it.format(builder) }
        builder.append(") VALUES ")
        sepBy(values.iterator(), builder.string, ", ") {
            builder.append('(')
            sepBy(it.iterator(), builder.string, ", ") {
                builder.argument(it)
            }
            builder.append(')')
        }

        if(isReplace) {
            builder.append("ON DUPLICATE KEY UPDATE ")
            sepBy(columns.iterator(), builder.string, ", ") {
                it.format(builder)
                builder.append("=VALUES(")
                it.format(builder)
                builder.append(')')
            }
        }

        builder.run(c, null, listenerData, f)
    }
}
