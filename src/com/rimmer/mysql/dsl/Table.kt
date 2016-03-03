package com.rimmer.mysql.dsl

import org.joda.time.DateTime
import java.util.*

interface FieldSet {
    val fields: List<Expression>
    val source: ColumnSet
}

interface ColumnSet: FieldSet {
    val columns: List<Column<*>>
    override val fields: List<Expression> get() = columns
    override val source: ColumnSet get() = this

    fun format(builder: QueryBuilder)

    fun slice(vararg columns: Expression): FieldSet = Slice(this, columns.toList())
    fun slice(columns: List<Expression>): FieldSet = Slice(this, columns)
}

class Slice(override val source: ColumnSet, override val fields: List<Expression>): FieldSet

open class Table(tableName: String? = null): ColumnSet {
    val name = tableName ?: javaClass.simpleName.removeSuffix("Table")
    val quotedName = "`$name`"

    override val columns = ArrayList<Column<*>>()

    fun integer(name: String): Column<Int> {
        val answer = Column(this, name, Int::class.java)
        columns.add(answer)
        return answer
    }

    fun short(name: String): Column<Short> {
        val answer = Column(this, name, Short::class.java)
        columns.add(answer)
        return answer
    }

    fun char(name: String): Column<Char> {
        val answer = Column(this, name, Char::class.java)
        columns.add(answer)
        return answer
    }

    fun float(name: String): Column<Float> {
        val answer = Column(this, name, Float::class.java)
        columns.add(answer)
        return answer
    }

    fun long(name: String): Column<Long> {
        val answer = Column(this, name, Long::class.java)
        columns.add(answer)
        return answer
    }

    fun date(name: String): Column<DateTime> {
        val answer = Column(this, name, DateTime::class.java)
        columns.add(answer)
        return answer
    }

    fun bool(name: String): Column<Boolean> {
        val answer = Column(this, name, Boolean::class.java)
        columns.add(answer)
        return answer
    }

    fun blob(name: String): Column<ByteArray> {
        val answer = Column(this, name, ByteArray::class.java)
        columns.add(answer)
        return answer
    }

    fun text(name: String): Column<String> {
        val answer = Column(this, name, String::class.java)
        columns.add(answer)
        return answer
    }

    fun <T:Any> Column<T>.nullable(): Column<T?> {
        val newColumn = Column(table, name, type as Class<T?>, true)
        return replaceColumn(this, newColumn)
    }

    override fun equals(other: Any?): Boolean {
        if(other !is Table) return false
        return other.name == name
    }

    override fun format(builder: QueryBuilder) { builder.append(quotedName) }
    override val fields: List<Expression> get() = columns

    private fun<T: Column<*>> replaceColumn(oldColumn: Column<*>, newColumn: T) : T {
        columns.remove(oldColumn)
        columns.add(newColumn)
        return newColumn
    }
}
