package com.rimmer.mysql.dsl

open class Column<T>(val table: Table, val name: String, type: Class<T>, nullable: Boolean = false) : TypedExpression<T>(type, nullable) {
    val quotedName = "`$name`"

    override fun equals(other: Any?): Boolean {
        return (other as? Column<*>)?.let {
            it.table == table && it.name == name && it.type == type
        } ?: false
    }

    override fun hashCode(): Int {
        return table.hashCode() * 31 + name.hashCode()
    }

    override fun toString(): String {
        return "$table.$name"
    }

    override fun format(builder: QueryBuilder) {
        builder.append(quotedName)
    }
}