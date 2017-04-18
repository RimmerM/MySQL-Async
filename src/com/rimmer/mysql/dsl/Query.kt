package com.rimmer.mysql.dsl

import com.rimmer.mysql.pool.ConnectionPool
import com.rimmer.mysql.protocol.Connection
import com.rimmer.mysql.protocol.QueryResult
import com.rimmer.mysql.protocol.decoder.intType

interface Query {
    /** Runs this query on the provided connection. */
    fun run(c: Connection, listenerData: Any? = null, f: (QueryResult?, Throwable?) -> Unit)

    /** Fetches a connection from the pool and runs this query. */
    fun run(pool: ConnectionPool, listenerData: Any? = null, f: (QueryResult?, Throwable?) -> Unit) {
        pool.get { c, e ->
            if(c == null) f(null, e)
            else run(c, listenerData) { r, e ->
                c.disconnect()
                f(r, e)
            }
        }
    }
}

fun FieldSet.select(where: Op<Boolean>) = Select(this, where)
fun FieldSet.selectAll() = Select(this, null)
fun FieldSet.selectCount(where: Op<Boolean>) = Select(this, where, true)
fun FieldSet.selectAllCount() = Select(this, null, true)

inline fun <T: Table> T.update(where: T.() -> Op<Boolean>, limit: Int? = null, ignore: Boolean = false) =
    Update(this, where(), ignore, limit)

inline fun <T: Table> T.update(where: T.() -> Op<Boolean>, limit: Int? = null, ignore: Boolean = false, f: T.(Update) -> Unit): Update {
    val u = Update(this, where(), ignore, limit)
    f(u)
    return u
}

fun Table.deleteAll() = Delete(this, null, false)

inline fun <T: Table> T.select(where: T.() -> Op<Boolean>) = Select(this, where())
inline fun <T: Table> T.selectCount(where: T.() -> Op<Boolean>) = Select(this, where(), true)

inline fun <T: Table> T.delete(where: T.() -> Op<Boolean>) = Delete(this, where(), false)

inline fun <T: Table> T.insert(isIgnore: Boolean = false, isReplace: Boolean = false, f: T.(Insert) -> Unit): Insert {
    val i = Insert(this, isIgnore, isReplace)
    f(i)
    return i
}

inline fun <T: Table, U> T.batchInsert(
    list: Iterable<U>,
    isIgnore: Boolean = false,
    isReplace: Boolean = false,
    f: T.(BatchInsert, U) -> Unit
): BatchInsert {
    val i = BatchInsert(this, isIgnore, isReplace)
    for(v in list) {
        i.addBatch()
        f(i, v)
    }
    return i
}

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
fun <T> Column<T>.sum() = Sum(this, intType)
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

infix fun <T: Any> TypedExpression<out T?>.eq(value: T?) = if(value === null) isNull() else EqOp(this, literal(value))
infix fun Expression.eq(rhs: Expression) = EqOp(this, rhs)
infix fun <T: Any> TypedExpression<out T?>.neq(value: T?) = if(value === null) isNotNull() else NeqOp(this, literal(value))
infix fun Expression.neq(rhs: Expression) = NeqOp(this, rhs)

infix fun Op<Boolean>.and(op: TypedExpression<Boolean>) = if(op is LiteralOp<Boolean> && op.value == true) this else AndOp(this, op)
infix fun Op<Boolean>.or(op: TypedExpression<Boolean>) = if(op is LiteralOp<Boolean> && op.value == false) this else OrOp(this, op)

infix fun <T> TypedExpression<T>.less(rhs: Expression) = LessOp(this, rhs)
infix fun <T: Any> TypedExpression<T>.less(rhs: T) = LessOp(this, literal(rhs))

infix fun <T> TypedExpression<T>.lessEq(rhs: Expression) = LessEqOp(this, rhs)
infix fun <T: Any> TypedExpression<T>.lessEq(rhs: T) = LessEqOp(this, literal(rhs))

infix fun <T> TypedExpression<T>.greater(rhs: Expression) = GreaterOp(this, rhs)
infix fun <T: Any> TypedExpression<T>.greater(rhs: T) = GreaterOp(this, literal(rhs))

infix fun <T> TypedExpression<T>.greaterEq(rhs: Expression) = GreaterEqOp(this, rhs)
infix fun <T: Any> TypedExpression<T>.greaterEq(rhs: T) = GreaterEqOp(this, literal(rhs))

operator fun <T, S: T> TypedExpression<T>.plus(rhs: TypedExpression<S>) = AddOp(this, rhs)
operator fun <T: Any> TypedExpression<T>.plus(rhs: T) = AddOp(this, literal(rhs))

operator fun <T, S: T> TypedExpression<T>.minus(rhs: TypedExpression<S>) = SubOp(this, rhs)
operator fun <T: Any> TypedExpression<T>.minus(rhs: T) = SubOp(this, literal(rhs))

operator fun <T, S: T> TypedExpression<T>.times(rhs: TypedExpression<S>) = MulOp(this, rhs)
operator fun <T: Any> TypedExpression<T>.times(rhs: T) = MulOp(this, literal(rhs))

operator fun <T, S: T> TypedExpression<T>.div(rhs: TypedExpression<S>) = DivOp(this, rhs)
operator fun <T: Any> TypedExpression<T>.div(rhs: T) = DivOp(this, literal(rhs))

infix fun <T: String?> TypedExpression<T>.like(pattern: String) = LikeOp(this, literal(pattern))
infix fun <T: String?> TypedExpression<T>.notLike(pattern: String) = NotLikeOp(this, literal(pattern))

infix fun <T: String?> TypedExpression<T>.regex(pattern: String) = RegexOp(this, literal(pattern))
infix fun <T: String?> TypedExpression<T>.notRegex(pattern: String) = NotRegexOp(this, literal(pattern))

infix fun <T: Any> Expression.inList(list: Iterable<T>) = InListOp(this, list, true)
infix fun <T: Any> Expression.notInList(list: Iterable<T>) = InListOp(this, list, false)

fun <T, S: Any> TypedExpression<out T?>.between(from: S, to: S) = Between(this, literal(from), literal(to))