package org.katas

fun sumPairs(n: Int, a: List<Int>): Pair<Int, Int>? =
    a.map { firstItem -> a.map { if (firstItem < it) Pair(firstItem, it) else Pair(it, firstItem) } }
        .flatten()
        .toSet()
        .filter { it.first != it.second && it.first + it.second == n }
        .let { if (it.size == 1) it.first() else null }





















