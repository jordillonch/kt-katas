package org.katas

class Stack<T> {
    private var data: MutableList<T> = mutableListOf()

    fun push(element: T) {
        data.add(0, element)
    }

    fun pop(): T {
        return data.get(0)
    }
}

fun main() {
    val stack = Stack<Int>()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    println("pop: ${stack.pop()}")
    println("pop: ${stack.pop()}")
    println("pop: ${stack.pop()}")
}
