package com.rimmer.mysql.dsl

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