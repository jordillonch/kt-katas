package org.katas

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DynamicProgrammingTest : StringSpec({
    "subarray sum" {
        subarraySum(listOf(23, 2, 4, 6, 7), 6) shouldBe true
        subarraySum(listOf(23, 2, 5, 6, 7), 6) shouldBe false
        subarraySum(listOf(1, 2, 3, 4), 7) shouldBe true
        subarraySum(listOf(1, 2, 3, 4), 10) shouldBe false
    }

    "equalSubsetSumPartition" {
        equalSubsetSumPartition(listOf(1, 2, 3, 4)) shouldBe true
        equalSubsetSumPartition(listOf(1, 1, 3, 4, 7)) shouldBe true
        equalSubsetSumPartition(listOf(2, 3, 4, 6)) shouldBe false
    }
})
