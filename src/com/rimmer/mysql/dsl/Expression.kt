package com.rimmer.mysql.dsl

import com.rimmer.mysql.protocol.Connection
import java.sql.ResultSet
import java.sql.Statement
import java.util.*

abstract class Expression<out T> {
    abstract fun format(builder: QueryBuilder)

    override fun equals(other: Any?): Boolean {
        return (other as? Expression<*>)?.toString() == toString()
    }

    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun toString(): String {
        val b = QueryBuilder()
        format(b)
        return b.toString()
    }
}

abstract class TypedExpression<T>(val type: Class<*>, val nullable: Boolean) : Expression<T>()