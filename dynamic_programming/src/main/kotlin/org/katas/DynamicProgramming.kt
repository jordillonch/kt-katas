package org.katas

import kotlin.math.max

/**
Continuous Subarray Sum
This is another popular dynamic programming-based coding problem from interviews.
In this problem, you will be given a list of non-negative numbers and a target integer k,
write a function to check if the array has a continuous subarray of size at least 2 that
sums up to a multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7], k=6

Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 */
fun subarraySum(input: List<Int>, k: Int): Boolean {
    return subarraySumRecursive(input, k, 0, 0)
}

fun subarraySumRecursive(input: List<Int>, k: Int, currentIndex: Int, selectedItems: Int): Boolean {
    if (k == 0 && selectedItems >= 2) return true
    if (currentIndex == input.size) return false
//    println("item: ${input[currentIndex]}, currentIndex: $currentIndex, k: $k, selectedItems: $selectedItems")

    // select current element
    var sumSelectingCurrentElement = false
    if (input[currentIndex] <= k) {
        sumSelectingCurrentElement =
            subarraySumRecursive(input, k - input[currentIndex], currentIndex + 1, selectedItems + 1)
    }
    // skip current element
    val sumSkippingCurrentElement = subarraySumRecursive(input, k, currentIndex + 1, selectedItems)

    println("sumSelectingCurrentElement: $sumSelectingCurrentElement, sumSkippingCurrentElement: $sumSkippingCurrentElement")
    return sumSelectingCurrentElement || sumSkippingCurrentElement
}
//fun subarraySum(input: List<Int>, k: Int): Boolean =
//    input.zipWithNext()
//        .firstOrNull { it.first + it.second == k }
//        .let { it != null }
//fun subarraySum(input: List<Int>, k: Int): Boolean {
//    for (i in input.indices) {
//        if (i < input.size - 1 && input[i] + input[i+1] == k) {
//            return true
//        }
//    }
//    return false
//}

/*
Given the weights and profits of āNā items, we are asked to put these items in a knapsack that has a capacity āCā. The
goal is to get the maximum profit from the items in the knapsack. Each item can only be selected once, as we donāt have
multiple quantities of any item.

Letās take Merryās example, who wants to carry some fruits in the knapsack to get maximum profit. Here are the weights
and profits of the fruits:

Items: { Apple, Orange, Banana, Melon }
Weights: { 2, 3, 1, 4 }
Profits: { 4, 5, 3, 7 }
Knapsack capacity: 5
 */
fun solveKnapsack(profits: IntArray, weights: IntArray, capacity: Int): Int {
    return solveKnapsackRecursive(profits, weights, capacity, 0)
}

fun solveKnapsackRecursive(profits: IntArray, weights: IntArray, capacity: Int, currentIndex: Int): Int {
    if (currentIndex >= profits.size || capacity == 0) return 0

    // select current item if capacity is >= item weight
    var profitWithCurrentItem = 0
    if (weights[currentIndex] <= capacity) {
        profitWithCurrentItem = profits[currentIndex] +
                solveKnapsackRecursive(profits, weights, capacity - weights[currentIndex], currentIndex + 1)
    }

    // skip current item
    val profitWithoutCurrentItem = solveKnapsackRecursive(profits, weights, capacity, currentIndex + 1)

    return max(profitWithCurrentItem, profitWithoutCurrentItem)
}


/*
9. Equal Subset Sum Partition Problem
This is another popular Dynamic Programming question that is very similar to the Knapsack
problem. If you know how to solve knapsack then you can solve this too.

In his problem you are given a set of positive numbers, find if we can partition it into two
subsets such that the sum of elements in both the subsets is equal.

Example 1:
Input: {1, 2, 3, 4}
Output: True
Explanation: The given set can be partitioned into two subsets with equal sum: {1, 4} & {2, 3}

Example 2:
Input: {1, 1, 3, 4, 7}
Output: True
Explanation: The given set can be partitioned into two subsets with equal sum: {1, 3, 4} & {1, 7}

Example 3:
Input: {2, 3, 4, 6}
Output: False
Explanation: The given set cannot be partitioned into two subsets with an equal sum.

Algorithm:
for each number 'i'
  create a new set which INCLUDES number 'i' if it does not exceed 'S/2', and recursively
      process the remaining numbers
  create a new set WITHOUT number 'i', and recursively process the remaining items
return true if any of the above sets has a sum equal to 'S/2', otherwise return false
 */
fun canPartition(nums: IntArray): Boolean {
    val sum = nums.sum()
    if (sum % 2 != 0) return false
    return canPartitionRecursive(nums, 0, sum / 2)
}

// {1, 2, 3, 4}
fun canPartitionRecursive(nums: IntArray, currentIndex: Int, sum: Int): Boolean {
    if (sum == 0) return true
    if (currentIndex == nums.size) return false

    // if it uses current item
    if (nums[currentIndex] <= sum) {
        if (canPartitionRecursive(nums, currentIndex + 1, sum - nums[currentIndex])) return true
    }

    // skip current item
    return canPartitionRecursive(nums, currentIndex + 1, sum)
}
// init {1, 2, 3, 4}
//
// index value sum
// 0     1     5
// 1     2     4
// 2     3     2
// 3     4     2

// 2     3     4

// 3     4     1
// 3     4     4


/**
Problem: You have to make a change of an amount using the smallest possible number of coins.
Amount: $18

Available coins are
$5 coin
$2 coin
$1 coin
There is no limit to the number of each coin you can use.
 */
fun makeChange(amount: Int): List<Int> {
    val coins = listOf(5, 2, 1)
    return makeChangeRecursive(amount, coins)
}

fun makeChangeRecursive(amount: Int, coins: List<Int>): List<Int> {
    if (amount == 0) return emptyList()
    val nextCoin = coins.first()
    return if (amount - nextCoin < 0) makeChangeRecursive(amount, coins.drop(1))
    else listOf(nextCoin) + makeChangeRecursive(amount - nextCoin, coins)
}


/**
Let's find the fibonacci sequence up to 5th term. A fibonacci series is the sequence of numbers in which each number is
the sum of the two preceding ones. For example, 0, 1, 1, 2, 3. Here, each number is the sum of the two preceding numbers.
 */
//fun fibonacciSerie(size: Int): List<Int> {
//    return fibonacciRecursive(size, 0, 1, emptyList())
//}
//
////element size first second  result
////0       5    0     1       []
////1       4    1     0+1     []+[0]
////2       3    0+1   1+(0+1) []+[0]+[0+1]
//fun fibonacciRecursive(size: Int, first: Int, second: Int, result: List<Int>): List<Int> {
//    if (size == 0) return result
//    return fibonacciRecursive(size - 1, second, first + second, result + first)
//}

fun fibonacciSerie(size: Int): List<Int> {
    return (0 until size).map { fibonacci(it) }
}

// index f(index)
// 0     0
// 1     1
// 2     0+1
// 3     1+(0+1)
// 4     (0+1)+(1+(0+1))
val fibonacciMemoize = mutableMapOf<Int, Int>()
fun fibonacci(index: Int): Int {
    if (!fibonacciMemoize.contains(index)) {
        fibonacciMemoize[index] = when (index) {
            0 -> 0
            1 -> 1
            else -> fibonacci(index - 2) + fibonacci(index - 1)
        }
    }
    return fibonacciMemoize[index]!!
}


/**
In this problem, you are climbing a staircase. It takes n steps to reach the top. Each time you can either climb 1 or
2 steps. The question is, in how many distinct ways can you climb to the top?

Input: n = 1
Output: 1
There is only one way to climb 1 stair

Input: n = 2
Output: 2
There are two ways: (1, 1) and (2)

Input: n = 3
Output: 3
(1, 1, 1), (1, 2), (2, 1)

Input: n = 4
Output: 5
(1, 1, 1, 1), (1, 1, 2), (2, 1, 1), (1, 2, 1), (2, 2)

Input: n = 5
Output: 8
(1, 1, 1, 1, 1), (2, 1, 1, 1), (1, 2, 1, 1), (1, 1, 2, 1), (1, 1, 1, 2), (1, 2, 2), (2, 1, 2), (2, 2, 1)

The above expression is actually the expression for Fibonacci numbers, but there is one thing to notice, the value of
ways(n) is equal to fibonacci(n+1).

ways(1) = fibonacci(2) = 1
ways(2) = fibonacci(3) = 2
ways(3) = fibonacci(4) = 3
ways(4) = fibonacci(5) = 5
 */
fun ways(stairsToClimb: Int): Int {
    return fibonacci(stairsToClimb + 1)
}


/**
Given two strings str1 and str2 and below operations that can be performed on str1. Find minimum number of edits
(operations) required to convert āstr1ā into āstr2ā.

Insert
Remove
Replace
All of the above operations are of equal cost.

Examples:

Input:   str1 = āgeekā, str2 = āgesekā
Output:  1
Explanation: We can convert str1 into str2 by inserting a āsā.

Input:   str1 = ācatā, str2 = ācutā
Output:  1
Explanation: We can convert str1 into str2 by replacing āaā with āuā.

Input:   str1 = āsundayā, str2 = āsaturdayā
Output:  3
Explanation: Last three and first characters are same.  We basically need to convert āunā to āaturā.  This can be done
using below three operations. Replace ānā with ārā, insert t, insert a
 */
fun minimumOfEdits(string1: String, string2: String): Int {
    return minimumOfEditsRecursive(string1, string2, string1.length, string2.length)
}

fun minimumOfEditsRecursive(string1: String, string2: String, len1: Int, len2: Int): Int {
    // If first string is empty, the only option is to insert all characters of second string into first
    if (len1 == 0) return len2
    // If second string is empty, the only option is to remove all characters of first string
    if (len2 == 0) return len1

    // If last characters of two strings are same, nothing much to do. Ignore last characters and get count for
    // remaining strings.
    if (string1[len1 - 1] == string2[len2 - 1]) {
        return minimumOfEditsRecursive(string1, string2, len1 - 1, len2 - 1)
    }

    // If last characters are not same, consider all three operations on last character of first string, recursively
    // compute minimum cost for all three operations and take minimum of three values.
    return 1 + listOf(
        minimumOfEditsRecursive(string1, string2, len1, len2 - 1), // insert
        minimumOfEditsRecursive(string1, string2, len1 - 1, len2), // delete
        minimumOfEditsRecursive(string1, string2, len1 - 1, len2 - 1), // replace
    ).min()
}
// gee
// ges
// len1 len2 operation str1
// 3    3
// 3    3-1  insert  = gees
// 3-1  3    delete  = ge
// 3-1  3-1  replace = ges


/**
Largest Sum Contiguous Subarray
Given an array arr[] of size N. The task is to find the sum of the contiguous subarray within a arr[] with the largest sum.

The idea of Kadaneās algorithm is to maintain a variable max_ending_here that stores the maximum sum contiguous
subarray ending at current index and a variable max_so_far stores the maximum sum of contiguous subarray found so far,
Everytime there is a positive-sum value in max_ending_here compare it with max_so_far and update max_so_far if it is
greater than max_so_far.
 */
fun largestSumContigousSubarray(a: IntArray): Int {
    var maxEndingHere = 0
    var maxSoFar = Int.MIN_VALUE
    for (item in a) {
        maxEndingHere += item
        if (maxEndingHere > maxSoFar) maxSoFar = maxEndingHere
        if (maxEndingHere < 0) maxEndingHere = 0
    }
    return maxSoFar
}
