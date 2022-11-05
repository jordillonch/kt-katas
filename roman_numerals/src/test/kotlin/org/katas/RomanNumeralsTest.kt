package org.katas

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class RomanNumeralsTest : ShouldSpec({
    should("print roman numerals") {
        romanNumerals(1) shouldBe "I"
        romanNumerals(2) shouldBe "II"
        romanNumerals(3) shouldBe "III"
        romanNumerals(4) shouldBe "IV"
        romanNumerals(5) shouldBe "V"
        romanNumerals(6) shouldBe "VI"
        romanNumerals(7) shouldBe "VII"
        romanNumerals(8) shouldBe "VIII"
        romanNumerals(9) shouldBe "IX"
        romanNumerals(10) shouldBe "X"
        romanNumerals(11) shouldBe "XI"
        romanNumerals(12) shouldBe "XII"
        romanNumerals(13) shouldBe "XIII"
        romanNumerals(14) shouldBe "XIV"
        romanNumerals(15) shouldBe "XV"
        romanNumerals(16) shouldBe "XVI"
        romanNumerals(17) shouldBe "XVII"
        romanNumerals(18) shouldBe "XVIII"
        romanNumerals(19) shouldBe "XIX"
        romanNumerals(20) shouldBe "XX"
    }

    should("print high roman numerals") {
        romanNumerals(49) shouldBe "IL"
        romanNumerals(50) shouldBe "L"
        romanNumerals(51) shouldBe "LI"
        romanNumerals(99) shouldBe "IC"
        romanNumerals(100) shouldBe "C"
        romanNumerals(101) shouldBe "CI"
        romanNumerals(499) shouldBe "ID"
        romanNumerals(500) shouldBe "D"
        romanNumerals(501) shouldBe "DI"
        romanNumerals(999) shouldBe "IM"
        romanNumerals(1000) shouldBe "M"
        romanNumerals(1001) shouldBe "MI"
        romanNumerals(2000) shouldBe "MM"
        romanNumerals(3000) shouldBe "MMM"
    }
})
