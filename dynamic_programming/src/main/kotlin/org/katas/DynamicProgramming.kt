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
fun subarraySum(input: List<Int>, k: Int): Boolean =
    input.zipWithNext()
        .firstOrNull { it.first + it.second == k }
        .let { it != null }
//fun subarraySum(input: List<Int>, k: Int): Boolean {
//    for (i in input.indices) {
//        if (i < input.size - 1 && input[i] + input[i+1] == k) {
//            return true
//        }
//    }
//    return false
//}

/*
Given the weights and profits of ‘N’ items, we are asked to put these items in a knapsack that has a capacity ‘C’. The
goal is to get the maximum profit from the items in the knapsack. Each item can only be selected once, as we don’t have
multiple quantities of any item.

Let’s take Merry’s example, who wants to carry some fruits in the knapsack to get maximum profit. Here are the weights
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

Input: n = 4
Output: 5
(1, 1, 1, 1), (1, 1, 2), (2, 1, 1), (1, 2, 1), (2, 2)

The above expression is actually the expression for Fibonacci numbers, but there is one thing to notice, the value of ways(n) is equal to fibonacci(n+1).

ways(1) = fibonacci(2) = 1
ways(2) = fibonacci(3) = 2
ways(3) = fibonacci(4) = 3
ways(4) = fibonacci(5) = 5
 */
fun ways(stairsToClimb: Int): Int {
    return fibonacci(stairsToClimb + 1)
}
