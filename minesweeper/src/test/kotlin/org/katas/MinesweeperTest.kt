package org.katas

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MinesweeperTest : FunSpec({
    val minesweeper = Minesweeper()

    listOf(true).forEach {
        test("$it should be true") {
            it shouldBe true
        }
    }
})
