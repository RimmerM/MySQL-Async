package com.rimmer.mysql.dsl

abstract class Expression {
    abstract fun format(builder: QueryBuilder)

    override fun equals(other: Any?): Boolean {
        return (other as? Expression)?.toString() == toString()
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

abstract class TypedExpression<T>(val type: Class<T>, val nullable: Boolean) : Expression()