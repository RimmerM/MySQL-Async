package com.rimmer.mysql.dsl

import org.joda.time.DateTime
import java.util.*

abstract class Function<T>(type: Class<T>): TypedExpression<T>(type, false)

class Count(val pivot: Expression<*>, val distinct: Boolean = false): Function<Long>(Long::class.java) {
    override fun format(builder: QueryBuilder) {
        builder.append("COUNT(")
        if(distinct) {
            builder.append("DISTINCT ")
        }
        pivot.format(builder)
        builder.append(')')
    }
}

class Date(val pivot: Expression<*>): Function<DateTime>(DateTime::class.java) {
    override fun format(builder: QueryBuilder) {
        builder.append("DATE(")
        pivot.format(builder)
        builder.append(')')
    }
}

class Month(val pivot: Expression<*>): Function<DateTime>(DateTime::class.java) {
    override fun format(builder: QueryBuilder) {
        builder.append("MONTH(")
        pivot.format(builder)
        builder.append(')')
    }
}

class Min<T>(val pivot: Expression<T>, type: Class<T>): Function<T>(type) {
    override fun format(builder: QueryBuilder) {
        builder.append("MIN(")
        pivot.format(builder)
        builder.append(')')
    }
}

class Max<T>(val pivot: Expression<T>, type: Class<T>): Function<T>(type) {
    override fun format(builder: QueryBuilder) {
        builder.append("MAX(")
        pivot.format(builder)
        builder.append(')')
    }
}

class Sum<T>(val pivot: Expression<T>, type: Class<T>): Function<T>(type) {
    override fun format(builder: QueryBuilder) {
        builder.append("SUM(")
        pivot.format(builder)
        builder.append(')')
    }
}

class Coalesce<T: Any>(val pivot: Expression<T?>, val alternate: TypedExpression<out T>, type: Class<T>): Function<T>(type) {
    override fun format(builder: QueryBuilder) {
        builder.append("COALESCE(")
        pivot.format(builder)
        builder.append(',')
        alternate.format(builder)
        builder.append(')')
    }
}

class Substring(val pivot: Expression<*>, val start: Expression<Int>, val length: Expression<Int>): Function<String>(String::class.java) {
    override fun format(builder: QueryBuilder) {
        builder.append("SUBSTRING(")
        pivot.format(builder)
        builder.append(',')
        start.format(builder)
        builder.append(',')
        length.format(builder)
    }
}

class Trim(val pivot: Expression<*>): Function<String>(String::class.java) {
    override fun format(builder: QueryBuilder) {
        builder.append("TRIM(")
        pivot.format(builder)
        builder.append(')')
    }
}

class Distinct<T>(val pivot: Expression<*>, type: Class<T>): Function<T>(type) {
    override fun format(builder: QueryBuilder) {
        builder.append("DISTINCT(")
        pivot.format(builder)
        builder.append(')')
    }
}

class Case<T>(val pivot: Expression<*>? = null): Expression<T>() {
    val cases = ArrayList<Pair<Expression<Boolean>, Expression<T>>>()
    var otherwise: Expression<T>? = null

    override fun format(builder: QueryBuilder) {
        if(otherwise == null) throw IllegalArgumentException("SQL when expressions must have a base case")

        builder.append("CASE ")
        pivot?.format(builder)

        for(w in cases) {
            builder.append(" WHEN ")
            w.first.format(builder)
            builder.append(" THEN ")
            w.second.format(builder)
            builder.append(" ELSE ")
            otherwise!!.format(builder)
            builder.append(" END")
        }
    }
}

class Now: Function<DateTime>(DateTime::class.java) {
    override fun format(builder: QueryBuilder) {
        builder.append("NOW()")
    }
}

class Rand: Function<Float>(Float::class.java) {
    override fun format(builder: QueryBuilder) {
        builder.append("RAND()")
    }
}