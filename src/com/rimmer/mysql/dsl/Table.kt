package com.rimmer.mysql.dsl

import org.joda.time.DateTime
import java.util.*

interface FieldSet {
    val fields: List<TypedExpression<*>>
    val source: ColumnSet
}

interface ColumnSet: FieldSet {
    val columns: List<Column<*>>
    override val fields: List<TypedExpression<*>> get() = columns
    override val source: ColumnSet get() = this

    fun format(builder: QueryBuilder)

    fun slice(vararg columns: TypedExpression<*>): FieldSet = Slice(this, columns.toList())
    fun slice(columns: List<TypedExpression<*>>): FieldSet = Slice(this, columns)
}

class Slice(override val source: ColumnSet, override val fields: List<TypedExpression<*>>): FieldSet

open class Table(name: String? = null): ColumnSet {
    val tableName = name ?: javaClass.simpleName.removeSuffix("Table")
    val quotedName = "`$tableName`"

    override val columns = ArrayList<Column<*>>()

    fun integer(name: String): Column<Int> {
        val answer = Column(this, name, Int::class.javaObjectType)
        columns.add(answer)
        return answer
    }

    fun short(name: String): Column<Short> {
        val answer = Column(this, name, Short::class.javaObjectType)
        columns.add(answer)
        return answer
    }

    fun char(name: String): Column<Char> {
        val answer = Column(this, name, Char::class.javaObjectType)
        columns.add(answer)
        return answer
    }

    fun float(name: String): Column<Float> {
        val answer = Column(this, name, Float::class.javaObjectType)
        columns.add(answer)
        return answer
    }

    fun long(name: String): Column<Long> {
        val answer = Column(this, name, Long::class.javaObjectType)
        columns.add(answer)
        return answer
    }

    fun date(name: String): Column<DateTime> {
        val answer = Column(this, name, DateTime::class.javaObjectType)
        columns.add(answer)
        return answer
    }

    fun bool(name: String): Column<Boolean> {
        val answer = Column(this, name, Boolean::class.javaObjectType)
        columns.add(answer)
        return answer
    }

    fun blob(name: String): Column<ByteArray> {
        val answer = Column(this, name, ByteArray::class.javaObjectType)
        columns.add(answer)
        return answer
    }

    fun text(name: String): Column<String> {
        val answer = Column(this, name, String::class.javaObjectType)
        columns.add(answer)
        return answer
    }

    fun <T:Any> Column<T>.nullable(): Column<T?> {
        val newColumn = Column(table, name, type as Class<T?>, true)
        return replaceColumn(this, newColumn)
    }

    override fun toString() = tableName

    override fun equals(other: Any?): Boolean {
        if(other !is Table) return false
        return other.tableName == tableName
    }

    override fun format(builder: QueryBuilder) { builder.append(quotedName) }
    override val fields: List<TypedExpression<*>> get() = columns

    private fun<T: Column<*>> replaceColumn(oldColumn: Column<*>, newColumn: T) : T {
        columns.remove(oldColumn)
        columns.add(newColumn)
        return newColumn
    }
}
