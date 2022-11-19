package org.katas

class LinkedList {
    var head: Node? = null

    data class Node(var data: Int, var next: Node? = null)

    fun insertAtBeginning(new_data: Int) {
        // insert the data
        val new_node = Node(new_data)
        new_node.next = head
        head = new_node
    }

    fun insertAfter(prev_node: Node?, new_data: Int) {
        if (prev_node == null) {
            println("The given previous node cannot be null")
            return
        }
        val new_node = Node(new_data)
        new_node.next = prev_node.next
        prev_node.next = new_node
    }

    fun insertAtEnd(new_data: Int) {
        val new_node = Node(new_data)
        if (head == null) {
            head = Node(new_data)
            return
        }
        new_node.next = null
        var last = head
        while (last!!.next != null) last = last.next
        last.next = new_node
    }

    fun deleteNode(position: Int) {
        if (head == null) return
        var temp = head
        if (position == 0) {
            head = temp!!.next
            return
        }
        // Find the key to be deleted
        var i = 0
        while (temp != null && i < position - 1) {
            temp = temp.next
            i++
        }

        // If the key is not present
        if (temp == null || temp.next == null) return

        // Remove the node
        val next = temp.next!!.next
        temp.next = next
    }

    fun search(head: Node?, key: Int): Boolean {
        var current = head
        while (current != null) {
            if (current.data === key) return true
            current = current.next
        }
        return false
    }

    fun sortLinkedList(head: Node?) {
        var current = head
        var index: Node? = null
        var temp: Int
        if (head == null) {
            return
        } else {
            while (current != null) {
                // index points to the node next to current
                index = current.next
                while (index != null) {
                    if (current.data > index.data) {
                        temp = current.data
                        current.data = index.data
                        index.data = temp
                    }
                    index = index.next
                }
                current = current.next
            }
        }
    }

    // Print the linked list
    fun printList() {
        var tnode = head
        while (tnode != null) {
            print("${tnode.data} ")
            tnode = tnode.next
        }
    }
}

fun main(args: Array<String>) {
    val llist = LinkedList()

    llist.insertAtEnd(1)
    llist.insertAtBeginning(2)
    llist.insertAtBeginning(3)
    llist.insertAtEnd(4)
    llist.insertAfter(llist.head!!.next, 5)

    println("Linked list: ")
    llist.printList()

    println("\nAfter deleting an element: ")
    llist.deleteNode(3)
    llist.printList()

    println()
    val item_to_find = 3
    if (llist.search(llist.head, item_to_find)) {
        println("$item_to_find is found")
    } else {
        println("$item_to_find is not found")
    }

    llist.sortLinkedList(llist.head)
    println("\nSorted List: ")
    llist.printList()
}

