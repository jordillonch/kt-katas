package org.katas

import java.util.*

class HashTable<K, V> {
    data class Node<K, V>(val key: K, var data: V)

    private val buckets: Int = 10
    private val hashList: List<MutableList<Node<K, V>?>> = (0..buckets).map { mutableListOf() }

    fun add(key: K, value: V) {
        val node = getNode(key)
        if (node == null) {
            hashList[bucketIndex(key)].add(Node(key, value))
        } else {
            node.data = value
        }
    }

    fun get(key: K): V? {
        return getNode(key)?.data
    }

    private fun getNode(key: K) = hashList[bucketIndex(key)].firstOrNull { it?.key == key }

    private fun hash(key: K): Int = Objects.hash(key)

    private fun bucketIndex(key: K): Int = hash(key) % buckets
}


//// Program to demonstrate implementation of our
//// own hash table with chaining for collision detection
//
//// A node of chains
//data class HashNode<K, V>(var key: K, var value: V, val hashCode: Int) {
//    // Reference to next node
//    var next: HashNode<K, V>? = null
//}
//
//// Class to represent entire hash table
//class HashTable<K, V> {
//    // bucketArray is used to store array of chains
//    private var bucketArray: ArrayList<HashNode<K, V>?>
//
//    // Current capacity of array list
//    private var numBuckets: Int
//
//    // Current size of array list
//    private var size: Int
//
//    // Constructor (Initializes capacity, size and
//    // empty chains.
//    init {
//        bucketArray = ArrayList()
//        numBuckets = 10
//        size = 0
//
//        // Create empty chains
//        for (i in 0 until numBuckets) bucketArray.add(null)
//    }
//
//    fun size(): Int {
//        return size
//    }
//
//    val isEmpty: Boolean
//        get() = size() == 0
//
//    private fun hashCode(key: K): Int {
//        return Objects.hashCode(key)
//    }
//
//    // This implements hash function to find index
//    // for a key
//    private fun getBucketIndex(key: K): Int {
//        val hashCode = hashCode(key)
//        var index = hashCode % numBuckets
//        // key.hashCode() could be negative.
//        index = if (index < 0) index * -1 else index
//        return index
//    }
//
//    // Method to remove a given key
//    fun remove(key: K): V? {
//        // Apply hash function to find index for given key
//        val bucketIndex = getBucketIndex(key)
//        val hashCode = hashCode(key)
//        // Get head of chain
//        var head = bucketArray[bucketIndex]
//
//        // Search for key in its chain
//        var prev: HashNode<K, V>? = null
//        while (head != null) {
//            // If Key found
//            if (head.key == key && hashCode == head.hashCode) break
//
//            // Else keep moving in chain
//            prev = head
//            head = head.next
//        }
//
//        // If key was not there
//        if (head == null) return null
//
//        // Reduce size
//        size--
//
//        // Remove key
//        if (prev != null) prev.next = head.next else bucketArray[bucketIndex] = head.next
//        return head.value
//    }
//
//    // Returns value for a key
//    operator fun get(key: K): V? {
//        // Find head of chain for given key
//        val bucketIndex = getBucketIndex(key)
//        val hashCode = hashCode(key)
//        var head = bucketArray[bucketIndex]
//
//        // Search key in chain
//        while (head != null) {
//            if (head.key == key && head.hashCode == hashCode) return head.value
//            head = head.next
//        }
//
//        // If key not found
//        return null
//    }
//
//    // Adds a key value pair to hash
//    fun add(key: K, value: V) {
//        // Find head of chain for given key
//        val bucketIndex = getBucketIndex(key)
//        val hashCode = hashCode(key)
//        var head = bucketArray[bucketIndex]
//
//        // Check if key is already present
//        while (head != null) {
//            if (head.key == key && head.hashCode == hashCode) {
//                head.value = value
//                return
//            }
//            head = head.next
//        }
//
//        // Insert key in chain
//        size++
//        head = bucketArray[bucketIndex]
//        val newNode = HashNode(key, value, hashCode)
//        newNode.next = head
//        bucketArray[bucketIndex] = newNode
//
//        // If load factor goes beyond threshold, then
//        // double hash table size
//        if (1.0 * size / numBuckets >= 0.7) {
//            val temp = bucketArray
//            bucketArray = ArrayList()
//            numBuckets = 2 * numBuckets
//            size = 0
//            for (i in 0 until numBuckets) bucketArray.add(null)
//            for (headNode in temp) {
//                var headNodeAux = headNode
//                while (headNodeAux != null) {
//                    add(headNodeAux.key, headNodeAux.value)
//                    headNodeAux = headNodeAux.next
//                }
//            }
//        }
//    }
//
//    companion object {
//        // Driver method to test HashTable class
//        @JvmStatic
//        fun main(args: Array<String>) {
//            val map: HashTable<String, Int> = HashTable()
//            map.add("this", 1)
//            map.add("coder", 2)
//            map.add("this", 4)
//            map.add("hi", 5)
//            println(map.size())
//            println(map.remove("this"))
//            println(map.remove("this"))
//            println(map.size())
//            println(map.isEmpty)
//        }
//    }
//}
