package org.katas

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class DiamondTest : ShouldSpec({
    val diamond = Diamond()

    should("play minesweeper") {
        diamond.play() shouldBe true
    }
})
