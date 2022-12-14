package org.katas

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DynamicProgrammingTest : StringSpec({
    "subarray sum" {
        subarraySum(listOf(23, 2, 4, 6, 7), 6) shouldBe true
        subarraySum(listOf(23, 2, 5, 6, 7), 6) shouldBe false
        subarraySum(listOf(1, 2, 3, 4), 7) shouldBe true
//        subarraySum(listOf(1, 2, 3, 4), 10) shouldBe false
        subarraySum(listOf(1, 2, 3, 4), 10) shouldBe true
    }

    "knapsack" {
        val profits = intArrayOf(1, 6, 10, 16)
        val weights = intArrayOf(1, 2, 3, 5)
        solveKnapsack(profits, weights, 7) shouldBe 22
        solveKnapsack(profits, weights, 6) shouldBe 17
    }

    "equalSubsetSumPartition" {
        canPartition(intArrayOf(1, 2, 3, 4)) shouldBe true
        canPartition(intArrayOf(1, 1, 3, 4, 7)) shouldBe true
        canPartition(intArrayOf(2, 3, 4, 6)) shouldBe false
    }

    "make change" {
        makeChange(18) shouldBe listOf(5, 5, 5, 2, 1)
        makeChange(19) shouldBe listOf(5, 5, 5, 2, 2)
    }

    "fibonacci" {
        fibonacciSerie(1) shouldBe listOf(0)
        fibonacciSerie(2) shouldBe listOf(0, 1)
        fibonacciSerie(3) shouldBe listOf(0, 1, 1)
        fibonacciSerie(4) shouldBe listOf(0, 1, 1, 2)
        fibonacciSerie(5) shouldBe listOf(0, 1, 1, 2, 3)
        fibonacciSerie(6) shouldBe listOf(0, 1, 1, 2, 3, 5)
    }

    "stairs to climb" {
        ways(1) shouldBe 1
        ways(2) shouldBe 2
        ways(3) shouldBe 3
        ways(4) shouldBe 5
        ways(5) shouldBe 8
    }

    "minimum of edits" {
        minimumOfEdits("geek", "gesek") shouldBe 1
        minimumOfEdits("cat", "cut") shouldBe 1
        minimumOfEdits("sunday", "saturday") shouldBe 3
    }

    "largest sum contiguous subarray" {
        largestSumContigousSubarray(intArrayOf(-2, -3, 4, -1, -2, 1, 5, -3)) shouldBe 7
        largestSumContigousSubarray(intArrayOf(10, -1, 9, -1)) shouldBe 18
        largestSumContigousSubarray(intArrayOf(4, -1, 2)) shouldBe 5
    }
})
