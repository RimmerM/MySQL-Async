package com.rimmer.mysql.dsl

import com.rimmer.mysql.protocol.decoder.booleanType

abstract class Op<T>(type: Class<T>, nullable: Boolean): TypedExpression<T>(type, nullable)

class LiteralOp<T: Any>(val value: T): TypedExpression<T>(value.javaClass, false) {
    override fun format(builder: QueryBuilder) {
        builder.argument(value)
    }
}

class AndOp(val lhs: TypedExpression<Boolean>, val rhs: TypedExpression<Boolean>): Op<Boolean>(lhs.type, lhs.nullable) {
    override fun format(builder: QueryBuilder) {
        builder.append('(')
        lhs.format(builder)
        builder.append(") and (")
        rhs.format(builder)
        builder.append(')')
    }
}

class OrOp(val lhs: TypedExpression<Boolean>, val rhs: TypedExpression<Boolean>): Op<Boolean>(lhs.type, lhs.nullable) {
    override fun format(builder: QueryBuilder) {
        builder.append('(')
        lhs.format(builder)
        builder.append(") or (")
        rhs.format(builder)
        builder.append(')')
    }
}

class Exists(val query: Select): Op<Boolean>(booleanType, false) {
    override fun format(builder: QueryBuilder) {
        builder.append("EXISTS (")
        query.format(builder)
        builder.append(')')
    }
}

class NotExists(val query: Select): Op<Boolean>(booleanType, false) {
    override fun format(builder: QueryBuilder) {
        builder.append("NOT EXISTS (")
        query.format(builder)
        builder.append(')')
    }
}

class IsNullOp(val lhs: Expression): Op<Boolean>(booleanType, false) {
    override fun format(builder: QueryBuilder) {
        lhs.format(builder)
        builder.append("IS NULL")
    }
}

class IsNotNullOp(val lhs: Expression): Op<Boolean>(booleanType, false) {
    override fun format(builder: QueryBuilder) {
        lhs.format(builder)
        builder.append("IS NOT NULL")
    }
}

class InListOp<out T: Any>(val pivot: Expression, val list: Iterable<T>, val inList: Boolean = true): Op<Boolean>(booleanType, false) {
    override fun format(builder: QueryBuilder) {
        val iterator = list.iterator()
        val hasFirst = iterator.hasNext()
        if(hasFirst) {
            val first = iterator.next()
            val hasSecond = iterator.hasNext()
            if(hasSecond) {
                pivot.format(builder)
                builder.append(if(inList) " IN (" else " NOT IN (")
                list.sepBy(builder.string, ",") {
                    builder.argument(it)
                }
                builder.append(')')
            } else {
                pivot.format(builder)
                builder.append(if(inList) " = " else " != ")
                builder.argument(first)
            }
        } else {
            builder.append(if(inList) " FALSE" else " TRUE")
        }
    }
}

class Between(val lhs: Expression, val from: LiteralOp<*>, val to: LiteralOp<*>): Op<Boolean>(booleanType, false) {
    override fun format(builder: QueryBuilder) {
        lhs.format(builder)
        builder.append(" BETWEEN (")
        from.format(builder)
        builder.append(") AND (")
        to.format(builder)
        builder.append(')')
    }
}

open class CompareOp(val lhs: Expression, val rhs: Expression, val op: String): Op<Boolean>(booleanType, false) {
    override fun format(builder: QueryBuilder) {
        builder.append('(')
        lhs.format(builder)
        builder.append(") ")
        builder.append(op)
        builder.append(" (")
        rhs.format(builder)
        builder.append(')')
    }
}

class EqOp(lhs: Expression, rhs: Expression): CompareOp(lhs, rhs, "=")
class NeqOp(lhs: Expression, rhs: Expression): CompareOp(lhs, rhs, "<>")
class LessOp(lhs: Expression, rhs: Expression): CompareOp(lhs, rhs, "<")
class LessEqOp(lhs: Expression, rhs: Expression): CompareOp(lhs, rhs, "<=")
class GreaterOp(lhs: Expression, rhs: Expression): CompareOp(lhs, rhs, ">")
class GreaterEqOp(lhs: Expression, rhs: Expression): CompareOp(lhs, rhs, ">=")
class LikeOp(lhs: Expression, rhs: Expression): CompareOp(lhs, rhs, "LIKE")
class NotLikeOp(lhs: Expression, rhs: Expression): CompareOp(lhs, rhs, "NOT LIKE")
class RegexOp(lhs: Expression, rhs: Expression): CompareOp(lhs, rhs, "REGEXP")
class NotRegexOp(lhs: Expression, rhs: Expression): CompareOp(lhs, rhs, "NOT REGEXP")

class AddOp<T, U: T>(val lhs: TypedExpression<T>, val rhs: TypedExpression<U>): TypedExpression<T>(lhs.type, false) {
    override fun format(builder: QueryBuilder) {
        builder.append('(')
        lhs.format(builder)
        builder.append(") + (")
        rhs.format(builder)
        builder.append(')')
    }
}

class SubOp<T, U: T>(val lhs: TypedExpression<T>, val rhs: TypedExpression<U>): TypedExpression<T>(lhs.type, false) {
    override fun format(builder: QueryBuilder) {
        builder.append('(')
        lhs.format(builder)
        builder.append(") - (")
        rhs.format(builder)
        builder.append(')')
    }
}

class MulOp<T, U: T>(val lhs: TypedExpression<T>, val rhs: TypedExpression<U>): TypedExpression<T>(lhs.type, false) {
    override fun format(builder: QueryBuilder) {
        builder.append('(')
        lhs.format(builder)
        builder.append(") * (")
        rhs.format(builder)
        builder.append(')')
    }
}

class DivOp<T, U: T>(val lhs: TypedExpression<T>, val rhs: TypedExpression<U>): TypedExpression<T>(lhs.type, false) {
    override fun format(builder: QueryBuilder) {
        builder.append('(')
        lhs.format(builder)
        builder.append(") / (")
        rhs.format(builder)
        builder.append(')')
    }
}

class BitAndOp<T, U: T>(val lhs: TypedExpression<T>, val rhs: TypedExpression<U>): TypedExpression<T>(lhs.type, false) {
    override fun format(builder: QueryBuilder) {
        builder.append('(')
        lhs.format(builder)
        builder.append(") & (")
        rhs.format(builder)
        builder.append(')')
    }
}

class BitOrOp<T, U: T>(val lhs: TypedExpression<T>, val rhs: TypedExpression<U>): TypedExpression<T>(lhs.type, false) {
    override fun format(builder: QueryBuilder) {
        builder.append('(')
        lhs.format(builder)
        builder.append(") | (")
        rhs.format(builder)
        builder.append(')')
    }
}