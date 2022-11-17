package org.katas

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
 */
fun equalSubsetSumPartition(input: List<Int>): Boolean {

}
