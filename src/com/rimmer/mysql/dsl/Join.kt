package com.rimmer.mysql.dsl

import java.util.*

enum class JoinType {
    INNER,
    LEFT,
    RIGHT,
    FULL
}

fun Table.join(
    table: Table, type: JoinType, on: Column<*>, with: Column<*>, leftColumns: Boolean = true, rightColumns: Boolean = true, constraint: (() -> Op<Boolean>)? = null
) = Join(this, table, type, on, with, leftColumns, rightColumns, constraint)

fun Table.innerJoin(
    table: Table, on: Column<*>, with: Column<*>, leftColumns: Boolean = true, rightColumns: Boolean = true, constraint: (() -> Op<Boolean>)? = null
) = Join(this, table, JoinType.INNER, on, with, leftColumns, rightColumns, constraint)

fun Table.leftJoin(
    table: Table, on: Column<*>, with: Column<*>, leftColumns: Boolean = true, rightColumns: Boolean = true, constraint: (() -> Op<Boolean>)? = null
) = Join(this, table, JoinType.LEFT, on, with, leftColumns, rightColumns, constraint)

class Join(val table: Table, columns: Boolean = false) : ColumnSet {
    val parts: ArrayList<JoinPart> = ArrayList()
    override val columns: ArrayList<Column<*>> = ArrayList()

    init {
        if(columns) {
            this.columns.addAll(table.columns)
        }
    }

    constructor(
        lhs: Table, rhs: Table, type: JoinType = JoinType.INNER, on: Column<*>, with: Column<*>,
        leftColumns: Boolean = true, rightColumns: Boolean = false, constraint: (() -> Op<Boolean>)? = null
    ) : this(lhs, leftColumns) {
        parts.add(JoinPart(type, rhs, on, with, constraint))
        if(rightColumns) {
            columns.addAll(rhs.columns)
        }
    }

    fun innerJoin(
        table: Table, on: Expression, with: Expression,
        columns: Boolean = true, constraint: (() -> Op<Boolean>)? = null
    ) = join(table, JoinType.INNER, on, with, columns, constraint)

    fun leftJoin(
        table: Table, on: Expression, with: Expression,
        columns: Boolean = true, constraint: (() -> Op<Boolean>)? = null
    ) = join(table, JoinType.LEFT, on, with, columns, constraint)

    fun join(
        rhs: Table, type: JoinType, on: Expression, other: Expression,
        columns: Boolean = true, constraint: (() -> Op<Boolean>)? = null
    ): Join {
        val newJoin = Join(table)
        newJoin.parts.addAll(parts)
        newJoin.parts.add(JoinPart(type, rhs, on, other, constraint))
        newJoin.columns.addAll(this.columns)

        if(columns) {
            newJoin.columns.addAll(rhs.columns)
        }
        return newJoin
    }

    override fun format(builder: QueryBuilder) {
        builder.append(table.tableName)
        for(p in parts) {
            builder.append(' ')
            builder.append(p.type.name)
            builder.append(" JOIN ")
            builder.append(p.table.quotedName)
            builder.append(" ON ")
            p.pkColumn.format(builder)
            builder.append(" = ")
            p.fkColumn.format(builder)
            p.constraint?.let {
                builder.append(" AND ")
                it().format(builder)
            }
            builder.append(' ')
        }
    }
}

class JoinPart(
    val type: JoinType, val table: Table, val pkColumn: Expression,
    val fkColumn: Expression, val constraint: (() -> Op<Boolean>)? = null
)