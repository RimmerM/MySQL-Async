package com.rimmer.mysql.dsl

import com.rimmer.mysql.protocol.Connection
import com.rimmer.mysql.protocol.QueryResult
import java.util.*

class Select(val set: FieldSet, val where: Op<Boolean>?): Expression() {
    val groupedBy = ArrayList<Expression>()
    val orderBy = ArrayList<Pair<Expression, Boolean>>()

    var having: Op<Boolean>? = null
    var limit: Int? = null
    var offset: Int? = null

    override fun format(builder: QueryBuilder) = with(builder) {
        append("SELECT ")

        set.fields.sepBy(string, ", ") {
            it.format(this)
        }

        append(" FROM ")
        set.source.format(this)

        if(where != null) {
            append(" WHERE ")
            where.format(this)
        }

        if(groupedBy.isNotEmpty()) {
            append(" GROUP BY ")
            groupedBy.sepBy(string, ", ") { it.format(this) }
        }

        if(having != null) {
            append(" HAVING ")
            having!!.format(builder)
        }

        if(orderBy.isNotEmpty()) {
            append(" ORDER BY ")
            orderBy.sepBy(builder.string, ", ") {
                it.first.format(this)
                append(if(it.second) " ASC" else " DESC")
            }
        }

        if(limit != null) {
            append(" LIMIT ")
            append(limit!!)

            if(offset != null) {
                append(" OFFSET ")
                append(offset!!)
            }
        }
    }

    fun run(c: Connection, f: (QueryResult?, Throwable?) -> Unit) {
        val builder = QueryBuilder()
        format(builder)
        builder.run(c, f)
    }
}