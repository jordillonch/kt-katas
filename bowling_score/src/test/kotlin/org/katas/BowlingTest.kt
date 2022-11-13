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
    should("calculate mixed scores from a real game") {
        calculate(
            listOf(
                listOf(5, 5),       // Spare
                listOf(4, 0),       // Simple
                listOf(8, 1),       // Simple
                listOf(10, 0),      // Strike
                listOf(0, 10),      // Spare
                listOf(10, 0),      // Strike
                listOf(10, 0),      // Strike
                listOf(10, 0),      // Strike
                listOf(4, 6),       // Spare
                listOf(10, 10, 5),  // Strike+Strike+Nothing
            )
        ) shouldBe 186
    }
    should("it should calculate simple score") {
        calculate(
            listOf(
                listOf(3, 4),
                listOf(4, 3),
                listOf(1, 2),
                listOf(2, 1),
                listOf(5, 1),
                listOf(1, 5),
                listOf(2, 5),
                listOf(3, 5),
                listOf(1, 1),
                listOf(1, 0),
            )
        ) shouldBe 50
    }
})
