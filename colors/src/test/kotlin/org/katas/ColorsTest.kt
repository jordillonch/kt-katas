package org.katas

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class ColorsTest : ShouldSpec({
    should("return red as nearest color") {
        Color("#FF0000").nearestColor() shouldBe listOf(BasePaletteColor.RED)
        Color("#FFFEFE").nearestColor() shouldBe listOf(BasePaletteColor.RED)
        Color("#FF1212").nearestColor() shouldBe listOf(BasePaletteColor.RED)
        Color("#FFFE00").nearestColor() shouldBe listOf(BasePaletteColor.RED)
        Color("#FF00FE").nearestColor() shouldBe listOf(BasePaletteColor.RED)
        Color("#020101").nearestColor() shouldBe listOf(BasePaletteColor.RED)
    }
    should("return green as nearest color") {
        Color("#00FF00").nearestColor() shouldBe listOf(BasePaletteColor.GREEN)
        Color("#FEFFFE").nearestColor() shouldBe listOf(BasePaletteColor.GREEN)
        Color("#21FF12").nearestColor() shouldBe listOf(BasePaletteColor.GREEN)
        Color("#FEFF00").nearestColor() shouldBe listOf(BasePaletteColor.GREEN)
        Color("#00FFFE").nearestColor() shouldBe listOf(BasePaletteColor.GREEN)
        Color("#010201").nearestColor() shouldBe listOf(BasePaletteColor.GREEN)
    }
    should("return blue as nearest color") {
        Color("#0000FF").nearestColor() shouldBe listOf(BasePaletteColor.BLUE)
        Color("#FEFEFF").nearestColor() shouldBe listOf(BasePaletteColor.BLUE)
        Color("#2112FF").nearestColor() shouldBe listOf(BasePaletteColor.BLUE)
        Color("#FE00FF").nearestColor() shouldBe listOf(BasePaletteColor.BLUE)
        Color("#00FEFF").nearestColor() shouldBe listOf(BasePaletteColor.BLUE)
        Color("#010102").nearestColor() shouldBe listOf(BasePaletteColor.BLUE)
    }
    should("return red as farthest color") {
        Color("#000101").farthestColor() shouldBe listOf(BasePaletteColor.RED)
        Color("#00F0F1").farthestColor() shouldBe listOf(BasePaletteColor.RED)
        Color("#FEFFFF").farthestColor() shouldBe listOf(BasePaletteColor.RED)
    }
    should("return green as farthest color") {
        Color("#010001").farthestColor() shouldBe listOf(BasePaletteColor.GREEN)
        Color("#F000F1").farthestColor() shouldBe listOf(BasePaletteColor.GREEN)
        Color("#FFFEFF").farthestColor() shouldBe listOf(BasePaletteColor.GREEN)
    }
    should("return blue as farthest color") {
        Color("#010100").farthestColor() shouldBe listOf(BasePaletteColor.BLUE)
        Color("#F0F100").farthestColor() shouldBe listOf(BasePaletteColor.BLUE)
        Color("#FFFFFE").farthestColor() shouldBe listOf(BasePaletteColor.BLUE)
    }
    should("return red and green as nearest color") {
        Color("#FFFF00").nearestColor() shouldBe listOf(BasePaletteColor.RED, BasePaletteColor.GREEN)
    }
    should("return red and blue as nearest color") {
        Color("#FF00FF").nearestColor() shouldBe listOf(BasePaletteColor.RED, BasePaletteColor.BLUE)
    }
    should("return green and blue as nearest color") {
        Color("#00FFFF").nearestColor() shouldBe listOf(BasePaletteColor.GREEN, BasePaletteColor.BLUE)
    }
    should("return all colors as nearest color") {
        Color("#FFFFFF").nearestColor() shouldBe listOf(
            BasePaletteColor.RED,
            BasePaletteColor.GREEN,
            BasePaletteColor.BLUE
        )
    }
    should("return red and green as farthest color") {
        Color("#0000FF").farthestColor() shouldBe listOf(BasePaletteColor.RED, BasePaletteColor.GREEN)
    }
    should("return red and blue as farthest color") {
        Color("#00FF00").farthestColor() shouldBe listOf(BasePaletteColor.RED, BasePaletteColor.BLUE)
    }
    should("return green and blue as farthest color") {
        Color("#FF0000").farthestColor() shouldBe listOf(BasePaletteColor.GREEN, BasePaletteColor.BLUE)
    }
    should("return all colors as farthest color") {
        Color("#000000").farthestColor() shouldBe listOf(
            BasePaletteColor.RED,
            BasePaletteColor.GREEN,
            BasePaletteColor.BLUE
        )
    }
})
