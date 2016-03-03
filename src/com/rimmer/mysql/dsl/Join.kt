package com.rimmer.mysql.dsl

import java.util.*

enum class JoinType {
    INNER,
    LEFT,
    RIGHT,
    FULL
}

fun Table.join(
    table: Table, type: JoinType, on: Column<*>, with: Column<*>, constraint: (() -> Op<Boolean>)? = null
) = Join(this, table, type, on, with, constraint)

fun Table.innerJoin(
    table: Table, on: Column<*>, with: Column<*>, constraint: (() -> Op<Boolean>)? = null
) = Join (this, table, JoinType.INNER, on, with, constraint)

fun Table.leftJoin(
    table: Table, on: Column<*>, with: Column<*>, constraint: (() -> Op<Boolean>)? = null
) = Join(this, table, JoinType.LEFT, on, with, constraint)

class Join(val table: Table) : ColumnSet {
    constructor(lhs: Table, rhs: Table, type: JoinType = JoinType.INNER, on: Column<*>, with: Column<*>, constraint: (() -> Op<Boolean>)? = null) : this(lhs) {
        parts.addAll(join(rhs, type, on, with, constraint).parts)
    }

    val parts: ArrayList<JoinPart> = ArrayList()

    fun innerJoin(
        table: Table, on: Expression, with: Expression, constraint: (() -> Op<Boolean>)? = null
    ) = join(table, JoinType.INNER, on, with, constraint)

    fun leftJoin(
        table: Table, on: Expression, with: Expression, constraint: (() -> Op<Boolean>)? = null
    ) = join(table, JoinType.LEFT, on, with, constraint)

    fun join(rhs: Table, type: JoinType, on: Expression, other: Expression, constraint: (() -> Op<Boolean>)? = null): Join {
        val newJoin = Join(table)
        newJoin.parts.addAll(parts)
        newJoin.parts.add(JoinPart(type, rhs, on, other, constraint))
        return newJoin
    }

    override fun format(builder: QueryBuilder) {
        builder.append(table.name)
        for(p in parts) {
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
        }
    }

    override val columns: List<Column<*>> get() {
        val answer = ArrayList<Column<*>>()
        answer.addAll(table.columns)
        for(p in parts) {
            answer.addAll(p.table.columns)
        }
        return answer
    }
}

class JoinPart(
    val type: JoinType, val table: Table, val pkColumn: Expression,
    val fkColumn: Expression, val constraint: (() -> Op<Boolean>)? = null
)