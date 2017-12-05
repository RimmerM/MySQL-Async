package com.rimmer.mysql.dsl

import com.rimmer.mysql.protocol.Connection
import com.rimmer.mysql.protocol.QueryResult
import java.util.*

class QueryBuilder {
    val string = StringBuilder()
    val args = ArrayList<Any?>()

    fun append(s: String) = string.append(s)
    fun append(c: Char) = string.append(c)
    fun append(b: Boolean) = string.append(if(b) "TRUE" else "FALSE")
    fun append(i: Int) = string.append(i)

    fun <T: Any> argument(arg: T?) {
        args.add(arg)
        string.append('?')
    }

    fun run(c: Connection, targetTypes: List<Class<*>>? = null, listenerData: Any? = null, f: (QueryResult?, Throwable?) -> Unit) {
        c.query(string.toString(), args, targetTypes, listenerData, f = f)
    }

    override fun toString() = string.toString()
}

inline fun <E> iterateLast(i: Iterator<E>, element: (E) -> Unit, last: (E) -> Unit) {
    if(i.hasNext()) {
        while(true) {
            val e = i.next()
            if(i.hasNext()) {
                element(e)
            } else {
                last(e)
                break
            }
        }
    }
}

inline fun <E> Iterable<E>.iterateLast(element: (E) -> Unit, last: (E) -> Unit) = iterateLast(iterator(), element, last)

inline fun <E> sepBy(i: Iterator<E>, string: StringBuilder, sep: String, f: (E) -> Unit) {
    iterateLast(i, {f(it); string.append(sep)}, {f(it)})
}

inline fun <E> Iterable<E>.sepBy(string: StringBuilder, sep: String, f: (E) -> Unit) = sepBy(iterator(), string, sep, f)