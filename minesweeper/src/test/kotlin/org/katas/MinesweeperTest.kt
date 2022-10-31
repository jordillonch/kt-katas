package org.katas

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class MinesweeperTest : ShouldSpec({
    val minesweeper = Minesweeper()

    should("play minesweeper") {
        minesweeper.play(
            """
            4 4
            *...
            ....
            .*..
            ....
            3 5
            **...
            .....
            .*...
            0 0
            """.trimIndent()
        ) shouldBe """
            Field #1:
            *100
            2210
            1*10
            1110
            
            Field #2:
            **100
            33200
            1*100
            """.trimIndent()
    }
})
