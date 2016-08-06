package com.rimmer.mysql.dsl

import com.rimmer.mysql.protocol.Connection
import com.rimmer.mysql.protocol.QueryResult

class Delete(val table: Table, val predicate: Op<Boolean>? = null, val isIgnore: Boolean = false): Query {
    override fun run(c: Connection, listenerData: Any?, f: (QueryResult?, Throwable?) -> Unit) {
        val builder = QueryBuilder()

        builder.append("DELETE ")
        if(isIgnore) { builder.append("IGNORE ") }
        builder.append("FROM ")
        table.format(builder)

        if(predicate != null) {
            builder.append(" WHERE ")
            predicate.format(builder)
        }

        builder.run(c, null, listenerData, f)
    }
}