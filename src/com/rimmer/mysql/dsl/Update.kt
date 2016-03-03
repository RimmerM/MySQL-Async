package com.rimmer.mysql.dsl

import com.rimmer.mysql.protocol.Connection
import com.rimmer.mysql.protocol.QueryResult
import java.util.*

class Update(val table: Table, val where: Op<Boolean>? = null, val limit: Int? = null): Query {
    val values = LinkedHashMap<Column<*>, Any?>()

    operator fun <T> set(column: Column<T>, value: T) {
        if(values.containsKey(column)) {
            throw IllegalArgumentException("$column has already been set")
        }
        values.put(column, value)
    }

    fun <T> set(column: Column<T>, value: Expression) {
        if(values.containsKey(column)) {
            throw IllegalArgumentException("$column has already been set")
        }
        values.put(column, value)
    }

    override fun run(c: Connection, f: (QueryResult?, Throwable?) -> Unit) {
        val builder = QueryBuilder()
        builder.append("UPDATE ")
        table.format(builder)
        builder.append(" SET ")
        sepBy(values.iterator(), builder.string, ", ") {
            it.key.format(builder)
            builder.append('=')
            val v = it.value
            if(v is Expression) {
                v.format(builder)
            } else {
                builder.argument(v)
            }
        }

        if(where != null) {
            builder.append(" WHERE ")
            where.format(builder)
        }

        if(limit != null) {
            builder.append(" LIMIT ")
            builder.append(limit)
        }

        builder.run(c, f)
    }
}
