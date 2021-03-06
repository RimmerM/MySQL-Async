package com.rimmer.mysql.dsl

import com.rimmer.mysql.protocol.Connection
import com.rimmer.mysql.protocol.QueryResult
import java.util.*

class Update(val table: Table, val where: Op<Boolean>? = null, val ignore: Boolean = false, val limit: Int? = null): Query {
    val values = LinkedHashMap<Column<*>, Any?>()

    operator fun <T> set(column: Column<T>, value: T) {
        values.put(column, value)
    }

    operator fun <T> set(column: Column<T>, value: Expression) {
        values.put(column, value)
    }

    override fun run(c: Connection, listenerData: Any?, f: (QueryResult?, Throwable?) -> Unit) {
        if(values.isEmpty()) {
            f(QueryResult(0, 0, "", 0, null), null)
            return
        }

        val builder = QueryBuilder()
        builder.append("UPDATE ")
        if(ignore) {
            builder.append("IGNORE ")
        }
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

        builder.run(c, null, listenerData, 0, null, f)
    }
}
