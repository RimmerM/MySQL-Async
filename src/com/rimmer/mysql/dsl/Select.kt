package com.rimmer.mysql.dsl

import com.rimmer.mysql.protocol.Connection
import com.rimmer.mysql.protocol.QueryResult
import com.rimmer.mysql.protocol.ResultSet
import com.rimmer.mysql.protocol.decoder.intType
import java.util.*
import kotlin.collections.ArrayList

class Select(val set: FieldSet, val where: Op<Boolean>?, val isCount: Boolean = false): Expression(), Query {
    var groupedBy: ArrayList<Expression>? = null
    var orderBy: ArrayList<Pair<Expression, Boolean>>? = null
    var forcedIndexes: ArrayList<String>? = null

    var having: Op<Boolean>? = null
    var limit: Int? = null
    var offset: Int? = null
    var forUpdate: Boolean = false

    override fun format(builder: QueryBuilder) = with(builder) {
        append("SELECT ")

        if(isCount) {
            append("COUNT(*)")
        } else {
            set.fields.sepBy(string, ", ") {
                it.format(this)
            }
        }

        append(" FROM ")
        set.source.format(this)

        val forcedIndexes = forcedIndexes
        if(forcedIndexes != null && forcedIndexes.isNotEmpty()) {
            append(" FORCE INDEX (")
            forcedIndexes.sepBy(string, ", ") {
                append('`')
                append(it)
                append('`')
            }
            append(')')
        }

        if(where != null) {
            append(" WHERE ")
            where.format(this)
        }

        val groupedBy = groupedBy
        if(groupedBy != null && groupedBy.isNotEmpty()) {
            append(" GROUP BY ")
            groupedBy.sepBy(string, ", ") { it.format(this) }
        }

        if(having != null) {
            append(" HAVING ")
            having!!.format(builder)
        }

        val orderBy = orderBy
        if(orderBy != null && orderBy.isNotEmpty()) {
            append(" ORDER BY ")
            orderBy.sepBy(builder.string, ", ") {
                it.first.format(this)
                append(if(it.second) " ASC" else " DESC")
            }
        }

        if(limit != null) {
            append(" LIMIT ")
            argument(limit!!)

            if(offset != null) {
                append(" OFFSET ")
                argument(offset!!)
            }
        }

        if(forUpdate) {
            append(" FOR UPDATE")
        }
    }

    override fun run(c: Connection, listenerData: Any?, f: (QueryResult?, Throwable?) -> Unit) {
        run(c, listenerData, 0, null, f)
    }

    override fun run(c: Connection, listenerData: Any?, chunkSize: Int, onResult: ((ResultSet) -> Unit)?, f: (QueryResult?, Throwable?) -> Unit) {
        val builder = QueryBuilder()
        val fields = if(isCount) listOf(intType) else set.fields.map {it.type}
        format(builder)
        builder.run(c, fields, listenerData, chunkSize, onResult, f)
    }

    fun forUpdate(): Select {
        forUpdate = true
        return this
    }

    infix fun groupBy(column: Expression): Select {
        if(groupedBy == null) groupedBy = ArrayList()
        groupedBy!!.add(column)
        return this
    }

    fun groupBy(vararg columns: Expression): Select {
        if(groupedBy == null) groupedBy = ArrayList()
        groupedBy!!.addAll(columns)
        return this
    }

    infix fun orderBy(column: Expression) = orderBy(column, true)

    fun orderBy(column: Expression, ascending: Boolean): Select {
        if(orderBy == null) orderBy = ArrayList()
        orderBy!!.add(Pair(column, ascending))
        return this
    }

    fun orderBy(vararg column: Pair<Expression, Boolean>): Select {
        if(orderBy == null) orderBy = ArrayList()
        orderBy!!.addAll(column)
        return this
    }

    fun forceIndex(vararg index: String): Select {
        if(forcedIndexes == null) forcedIndexes = ArrayList()
        forcedIndexes!!.addAll(index)
        return this
    }

    infix fun limit(count: Int): Select {
        limit = count
        return this
    }

    infix fun offset(index: Int): Select {
        offset = index
        return this
    }
}

class Union(val left: Select, val right: Expression, val all: Boolean): Expression(), Query {
    val orderBy = ArrayList<Pair<Expression, Boolean>>()
    var limit: Int? = null
    var offset: Int? = null

    override fun format(builder: QueryBuilder) = with(builder) {
        val chain = right is Union

        append('(')
        left.format(builder)
        append(if(all) ") UNION ALL " else ") UNION ")
        if(!chain) append('(')
        right.format(builder)
        if(!chain) append(')')

        if(orderBy.isNotEmpty()) {
            append(" ORDER BY ")
            orderBy.sepBy(builder.string, ", ") {
                it.first.format(this)
                append(if(it.second) " ASC" else " DESC")
            }
        }

        if(limit != null) {
            append(" LIMIT ")
            argument(limit!!)

            if(offset != null) {
                append(" OFFSET ")
                argument(offset!!)
            }
        }
    }

    override fun run(c: Connection, listenerData: Any?, f: (QueryResult?, Throwable?) -> Unit) {
        run(c, listenerData, 0, null, f)
    }

    override fun run(c: Connection, listenerData: Any?, chunkSize: Int, onResult: ((ResultSet) -> Unit)?, f: (QueryResult?, Throwable?) -> Unit) {
        val builder = QueryBuilder()
        val fields = left.set.fields.map {it.type}
        format(builder)
        builder.run(c, fields, listenerData, chunkSize, onResult, f)
    }

    infix fun orderBy(column: Expression) = orderBy(column, true)

    fun orderBy(column: Expression, ascending: Boolean): Union {
        orderBy.add(Pair(column, ascending))
        return this
    }

    fun orderBy(vararg column: Pair<Expression, Boolean>): Union {
        orderBy.addAll(column)
        return this
    }

    infix fun limit(count: Int): Union {
        limit = count
        return this
    }

    infix fun offset(index: Int): Union {
        offset = index
        return this
    }
}

fun union(left: Select, right: Expression) = Union(left, right, false)
fun unionAll(left: Select, right: Expression) = Union(left, right, true)
