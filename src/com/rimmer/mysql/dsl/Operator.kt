package com.rimmer.mysql.dsl

abstract class Op<T>: Expression<T>()

class LiteralOp<T>(val value: Any): TypedExpression<T>(value.javaClass, false) {
    override fun format(builder: QueryBuilder) {
        builder.argument(value)
    }
}

infix fun Op<Boolean>.and(op: Expression<Boolean>) =
    if(op is LiteralOp<Boolean> && op.value == true) this else AndOp(this, op)

infix fun Op<Boolean>.or(op: Expression<Boolean>) =
    if(op is LiteralOp<Boolean> && op.value == false) this else OrOp(this, op)

class AndOp(val lhs: Expression<Boolean>, val rhs: Expression<Boolean>): Op<Boolean>() {
    override fun format(builder: QueryBuilder) {
        if(lhs is OrOp) {
            builder.append('(')
            lhs.format(builder)
            builder.append(')')
        } else {
            lhs.format(builder)
        }

        builder.append(" and ")

        if(rhs is OrOp) {
            builder.append('(')
            rhs.format(builder)
            builder.append(')')
        } else {
            rhs.format(builder)
        }
    }
}

class OrOp(val lhs: Expression<Boolean>, val rhs: Expression<Boolean>): Op<Boolean>() {
    override fun format(builder: QueryBuilder) {
        builder.append('(')
        lhs.format(builder)
        builder.append(") or (")
        rhs.format(builder)
        builder.append(')')
    }
}

class exists(val query: Select): Op<Boolean>() {
    override fun format(builder: QueryBuilder) {
        builder.append("EXISTS (")
        query.format(builder)
        builder.append(')')
    }
}

class notExists(val query: Select): Op<Boolean>() {
    override fun format(builder: QueryBuilder) {
        builder.append("NOT EXISTS (")
        query.format(builder)
        builder.append(')')
    }
}

class IsNullOp(val lhs: Expression<Boolean>): Op<Boolean>() {
    override fun format(builder: QueryBuilder) {
        lhs.format(builder)
        builder.append("IS NULL")
    }
}

class IsNotNullOp(val lhs: Expression<Boolean>): Op<Boolean>() {
    override fun format(builder: QueryBuilder) {
        lhs.format(builder)
        builder.append("IS NOT NULL")
    }
}

class InListOp<T: Any>(val pivot: Expression<*>, val list: List<T>, val inList: Boolean = true): Op<Boolean>() {
    override fun format(builder: QueryBuilder) {
        when(list.size) {
            0 -> builder.append(if(inList) " FALSE" else " TRUE")
            1 -> {
                pivot.format(builder)
                builder.append(if(inList) " = " else " != ")
                builder.argument(list.first())
            }
            else -> {
                pivot.format(builder)
                builder.append(if(inList) " IN (" else " NOT IN (")
                list.sepBy(builder.string, ",") {
                    builder.argument(it)
                }
                builder.append(')')
            }
        }
    }
}

class Between(val lhs: Expression<Boolean>, val from: LiteralOp<*>, val to: LiteralOp<*>): Op<Boolean>() {
    override fun format(builder: QueryBuilder) {
        lhs.format(builder)
        builder.append(" BETWEEN ")
        from.format(builder)
        builder.append(" AND ")
        to.format(builder)
    }
}

open class CompareOp(val lhs: Expression<*>, val rhs: Expression<*>, val op: String): Op<Boolean>() {
    override fun format(builder: QueryBuilder) {
        if(lhs is OrOp) {
            builder.append('(')
            lhs.format(builder)
            builder.append(')')
        } else {
            lhs.format(builder)
        }

        builder.append(' ')
        builder.append(op)
        builder.append(' ')

        if(rhs is OrOp) {
            builder.append('(')
            rhs.format(builder)
            builder.append(')')
        } else {
            rhs.format(builder)
        }
    }
}

class EqOp(lhs: Expression<*>, rhs: Expression<*>): CompareOp(lhs, rhs, "=")
class NeqOp(lhs: Expression<*>, rhs: Expression<*>): CompareOp(lhs, rhs, "<>")
class LessOp(lhs: Expression<*>, rhs: Expression<*>): CompareOp(lhs, rhs, "<")
class LessEqOp(lhs: Expression<*>, rhs: Expression<*>): CompareOp(lhs, rhs, "<=")
class GreaterOp(lhs: Expression<*>, rhs: Expression<*>): CompareOp(lhs, rhs, ">")
class GreaterEqOp(lhs: Expression<*>, rhs: Expression<*>): CompareOp(lhs, rhs, ">=")
class LikeOp(lhs: Expression<*>, rhs: Expression<*>): CompareOp(lhs, rhs, "LIKE")
class NotLikeOp(lhs: Expression<*>, rhs: Expression<*>): CompareOp(lhs, rhs, "NOT LIKE")
class RegexOp(lhs: Expression<*>, rhs: Expression<*>): CompareOp(lhs, rhs, "REGEXP")
class NotRegexOp(lhs: Expression<*>, rhs: Expression<*>): CompareOp(lhs, rhs, "NOT REGEXP")

class AddOp<T, U: T>(val lhs: Expression<T>, val rhs: Expression<U>, type: Class<*>): TypedExpression<T>(type, false) {
    override fun format(builder: QueryBuilder) {
        lhs.format(builder)
        builder.append('+')
        rhs.format(builder)
    }
}

class SubOp<T, U: T>(val lhs: Expression<T>, val rhs: Expression<U>, type: Class<*>): TypedExpression<T>(type, false) {
    override fun format(builder: QueryBuilder) {
        lhs.format(builder)
        builder.append('-')
        rhs.format(builder)
    }
}

class MulOp<T, U: T>(val lhs: Expression<T>, val rhs: Expression<U>, type: Class<*>): TypedExpression<T>(type, false) {
    override fun format(builder: QueryBuilder) {
        builder.append('(')
        lhs.format(builder)
        builder.append(") * (")
        rhs.format(builder)
        builder.append(')')
    }
}

class DivOp<T, U: T>(val lhs: Expression<T>, val rhs: Expression<U>, type: Class<*>): TypedExpression<T>(type, false) {
    override fun format(builder: QueryBuilder) {
        builder.append('(')
        lhs.format(builder)
        builder.append(") / (")
        rhs.format(builder)
        builder.append(')')
    }
}

class BitAndOp<T, U: T>(val lhs: Expression<T>, val rhs: Expression<U>, type: Class<*>): TypedExpression<T>(type, false) {
    override fun format(builder: QueryBuilder) {
        builder.append('(')
        lhs.format(builder)
        builder.append(") & (")
        rhs.format(builder)
        builder.append(')')
    }
}

class BitOrOp<T, U: T>(val lhs: Expression<T>, val rhs: Expression<U>, type: Class<*>): TypedExpression<T>(type, false) {
    override fun format(builder: QueryBuilder) {
        builder.append('(')
        lhs.format(builder)
        builder.append(") | (")
        rhs.format(builder)
        builder.append(')')
    }
}