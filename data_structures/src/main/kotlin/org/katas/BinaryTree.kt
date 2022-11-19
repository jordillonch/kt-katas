package org.katas

// Binary Tree
// Node creation
internal class Node(var key: Int) {
    var left: Node?
    var right: Node? = null

    init {
        left = right
    }
}

internal class BinaryTree {
    var root: Node?

    constructor(key: Int) {
        root = Node(key)
    }

    constructor() {
        root = null
    }

    // Traverse Inorder
    fun traverseInOrder(node: Node?) {
        if (node != null) {
            traverseInOrder(node.left)
            print(" " + node.key)
            traverseInOrder(node.right)
        }
    }

    // Traverse Postorder
    fun traversePostOrder(node: Node?) {
        if (node != null) {
            traversePostOrder(node.left)
            traversePostOrder(node.right)
            print(" " + node.key)
        }
    }

    // Traverse Preorder
    fun traversePreOrder(node: Node?) {
        if (node != null) {
            print(" " + node.key)
            traversePreOrder(node.left)
            traversePreOrder(node.right)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val tree = BinaryTree()
            tree.root = Node(1)
            tree.root!!.left = Node(2)
            tree.root!!.right = Node(3)
            tree.root!!.left!!.left = Node(4)
            print("Pre order Traversal: ")
            tree.traversePreOrder(tree.root)
            print("\nIn order Traversal: ")
            tree.traverseInOrder(tree.root)
            print("\nPost order Traversal: ")
            tree.traversePostOrder(tree.root)
        }
    }
}
