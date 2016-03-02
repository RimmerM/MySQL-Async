package com.rimmer.mysql.dsl

import com.rimmer.mysql.protocol.Connection
import com.rimmer.mysql.protocol.QueryResult
import java.util.*

fun FieldSet.select(where: Op<Boolean>) = Select(this, where)

class QueryBuilder() {
    val string = StringBuilder()
    val args = ArrayList<Any?>()

    fun append(s: String) = string.append(s)
    fun append(c: Char) = string.append(c)
    fun append(b: Boolean) = string.append(if(b) "TRUE" else "FALSE")
    fun append(i: Int) = string.append(i)

    fun <T: Any> argument(arg: T?) {
        args.add(arg)
        string.append('?')
    }

    fun run(c: Connection, f: (QueryResult?, Throwable?) -> Unit) {
        c.query(string.toString(), args, null, f)
    }

    override fun toString() = string.toString()
}

