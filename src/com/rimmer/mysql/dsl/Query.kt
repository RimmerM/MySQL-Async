package com.rimmer.mysql.dsl

import com.rimmer.mysql.pool.ConnectionPool
import com.rimmer.mysql.protocol.Connection
import com.rimmer.mysql.protocol.QueryResult

infix fun <T, U, E> ((T?, E?) -> U).bind(f: (U?, E?) -> Unit) {

}

interface Query {
    /** Runs this query on the provided connection. */
    fun run(c: Connection, f: (QueryResult?, Throwable?) -> Unit)

    /** Fetches a connection from the pool and runs this query. */
    fun run(c: ConnectionPool, f: (QueryResult?, Throwable?) -> Unit) {
        c.get { c, e ->
            if(c == null) f(null, e)
            else run(c, f)
        }


        c.get(bind(f))
    }
}

fun FieldSet.select(where: Op<Boolean>) = Select(this, where)
fun FieldSet.selectAll() = Select(this, null)

inline fun <T: Table> T.update(where: T.() -> Op<Boolean>, limit: Int? = null) = Update(this, where(), limit)

fun Table.deleteAll() = Delete(this, null, false)

inline fun <T: Table> T.select(where: T.() -> Op<Boolean>) = Select(this, where())
inline fun <T: Table> T.delete(where: T.() -> Op<Boolean>) = Delete(this, where(), false)

/*
 * Function helpers.
 */

fun <T: Any> literal(value: T) = LiteralOp(value)
val random = Rand()
val now = Now()

fun Column<*>.count() = Count(this)
fun Column<*>.distinctCount() = Count(this, true)
fun <T> Column<T>.distinct() = Distinct(this, type)
fun <T> Column<T>.min() = Min(this, type)
fun <T> Column<T>.max() = Max(this, type)
fun <T> Column<T>.sum() = Sum(this, type)
fun <T: String?> Column<T>.trim() = Trim(this)
fun <T: String?> Column<T>.substring(start: Int, length: Int) = Substring(this, literal(start), literal(length))

fun Expression.date() = Date(this)
fun Expression.month() = Month(this)
fun <T: Any> Expression.coalesce(alternate: TypedExpression<T>) = Coalesce(this, alternate, alternate.type)

/*
 * Operator helpers.
 */

fun <T> TypedExpression<T>.isNull() = IsNullOp(this)
fun <T> TypedExpression<T>.isNotNull() = IsNotNullOp(this)

infix fun <T: Any> TypedExpression<T>.eq(value: T?) = if(value === null) isNull() else EqOp(this, literal(value))
infix fun Expression.eq(rhs: Expression) = EqOp(this, rhs)
infix fun <T: Any> TypedExpression<T>.neq(value: T?) = if(value === null) isNotNull() else NeqOp(this, literal(value))
infix fun Expression.neq(rhs: Expression) = NeqOp(this, rhs)

infix fun Op<Boolean>.and(op: TypedExpression<Boolean>) = if(op is LiteralOp<Boolean> && op.value == true) this else AndOp(this, op)
infix fun Op<Boolean>.or(op: TypedExpression<Boolean>) = if(op is LiteralOp<Boolean> && op.value == false) this else OrOp(this, op)

