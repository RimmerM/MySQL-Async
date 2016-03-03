package com.rimmer.mysql.dsl

import com.rimmer.mysql.protocol.Connection
import com.rimmer.mysql.protocol.QueryResult
import java.util.*

interface Query {
    fun run(c: Connection, f: (QueryResult?, Throwable?) -> Unit)
}

fun FieldSet.select(where: Op<Boolean>) = Select(this, where)
