package com.rimmer.mysql.dsl

import com.rimmer.mysql.protocol.Connection
import com.rimmer.mysql.protocol.QueryResult
import java.util.*

class Insert(val table: Table, val isIgnore: Boolean = false, val isReplace: Boolean = false) {
    val values = LinkedHashMap<Column<*>, Any?>()

    operator fun <T> set(column: Column<T>, value: T) {
        if(values.containsKey(column)) {
            throw IllegalArgumentException("$column has already been set")
        }
        values.put(column, value)
    }

    fun run(c: Connection, f: (QueryResult?, Throwable?) -> Unit) {
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

        builder.run(c, f)
    }
}
