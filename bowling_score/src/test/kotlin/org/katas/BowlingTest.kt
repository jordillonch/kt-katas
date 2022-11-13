package org.katas

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class BowlingTest : ShouldSpec({

    should("Example 1: Gutter balls (all zero)") {
        calculate(
            listOf(
                listOf(0, 0),
                listOf(0, 0),
                listOf(0, 0),
                listOf(0, 0),
                listOf(0, 0),
                listOf(0, 0),
                listOf(0, 0),
                listOf(0, 0),
                listOf(0, 0),
                listOf(0, 0)
            )
        ) shouldBe 0
    }
    should("Example 2: All Threes") {
        calculate(
            listOf(
                listOf(3, 3),
                listOf(3, 3),
                listOf(3, 3),
                listOf(3, 3),
                listOf(3, 3),
                listOf(3, 3),
                listOf(3, 3),
                listOf(3, 3),
                listOf(3, 3),
                listOf(3, 3)
            )
        ) shouldBe 60
    }
    should("Example 3: All Spares with first ball a 4") {
        calculate(
            listOf(
                listOf(4, 6),
                listOf(4, 6),
                listOf(4, 6),
                listOf(4, 6),
                listOf(4, 6),
                listOf(4, 6),
                listOf(4, 6),
                listOf(4, 6),
                listOf(4, 6),
                listOf(4, 6, 4)
            )
        ) shouldBe 140
    }
    should("Example 4: Nine Strikes followed by a gutter ball") {
        calculate(
            listOf(
                listOf(10, 0),
                listOf(10, 0),
                listOf(10, 0),
                listOf(10, 0),
                listOf(10, 0),
                listOf(10, 0),
                listOf(10, 0),
                listOf(10, 0),
                listOf(10, 0),
                listOf(0, 0)
            )
        ) shouldBe 240
    }
    should("Example 5: Perfect Game") {
        calculate(
            listOf(
                listOf(10, 0),
                listOf(10, 0),
                listOf(10, 0),
                listOf(10, 0),
                listOf(10, 0),
                listOf(10, 0),
                listOf(10, 0),
                listOf(10, 0),
                listOf(10, 0),
                listOf(10, 10, 10)
            )
        ) shouldBe 300
    }
})
