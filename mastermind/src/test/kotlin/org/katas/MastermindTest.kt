package org.katas

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class MastermindTest : ShouldSpec({
    should("find 0 well placed and 0 misplaced for 1 colors secret") {
        val mastermind = Mastermind(listOf("blue"))
        mastermind.evaluate(listOf("red")) shouldBe Pair(0, 0)
    }
    should("find 1 well placed and 0 misplaced for 1 colors secret") {
        val mastermind = Mastermind(listOf("blue"))
        mastermind.evaluate(listOf("blue")) shouldBe Pair(1, 0)
    }
    should("find 0 well placed and 1 misplaced for 1 colors secret") {
        val mastermind = Mastermind(listOf("red", "yellow"))
        mastermind.evaluate(listOf("blue", "red")) shouldBe Pair(0, 1)
    }
    should("find 1 well placed and 1 misplaced for 4 colors secret") {
        val mastermind = Mastermind(listOf("blue", "red", "green", "pink"))
        mastermind.evaluate(listOf("yellow", "red", "blue", "purple")) shouldBe Pair(1, 1)
    }
    should("find 1 well placed and 2 misplaced for 4 colors secret") {
        val mastermind = Mastermind(listOf("blue", "green", "blue", "green"))
        mastermind.evaluate(listOf("green", "green", "red", "blue")) shouldBe Pair(1, 2)
    }
    should("find 0 well placed and 0 misplaced for 4 colors secret") {
        val mastermind = Mastermind(listOf("yellow", "blue", "yellow", "blue", "yellow"))
        mastermind.evaluate(listOf("blue", "yellow", "red", "yellow", "blue")) shouldBe Pair(0, 4)
    }
    should("find 1 well placed and 3 misplaced for 5 colors secret") {
        val mastermind = Mastermind(listOf("yellow", "blue", "red", "blue", "red"))
        mastermind.evaluate(        listOf("blue", "yellow", "red", "yellow", "blue")) shouldBe Pair(1, 3)
    }
})
