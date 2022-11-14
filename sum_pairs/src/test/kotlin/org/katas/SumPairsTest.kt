package org.katas

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class SumPairsTest : ShouldSpec({
    should("find no solution because any pair can sum n") {
        sumPairs(2, listOf(1, 3)) shouldBe null
    }
    should("find the unique solution") {
        sumPairs(8, listOf(1, 2, 7, 9)) shouldBe Pair(1, 7)
    }
    should("find no solution because not a unique solution") {
        sumPairs(8, listOf(1, 2, 6, 7, 9)) shouldBe null
    }
})
