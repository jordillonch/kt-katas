package org.katas

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class RomanNumeralsTest : ShouldSpec({
    val romanNumerals = RomanNumerals()
    should("print roman numerals") {
        romanNumerals.toSymbols(1) shouldBe "I"
        romanNumerals.toSymbols(2) shouldBe "II"
        romanNumerals.toSymbols(3) shouldBe "III"
        romanNumerals.toSymbols(4) shouldBe "IV"
        romanNumerals.toSymbols(5) shouldBe "V"
        romanNumerals.toSymbols(6) shouldBe "VI"
        romanNumerals.toSymbols(7) shouldBe "VII"
        romanNumerals.toSymbols(8) shouldBe "VIII"
        romanNumerals.toSymbols(9) shouldBe "IX"
        romanNumerals.toSymbols(10) shouldBe "X"
        romanNumerals.toSymbols(11) shouldBe "XI"
        romanNumerals.toSymbols(12) shouldBe "XII"
        romanNumerals.toSymbols(13) shouldBe "XIII"
        romanNumerals.toSymbols(14) shouldBe "XIV"
        romanNumerals.toSymbols(15) shouldBe "XV"
        romanNumerals.toSymbols(16) shouldBe "XVI"
        romanNumerals.toSymbols(17) shouldBe "XVII"
        romanNumerals.toSymbols(18) shouldBe "XVIII"
        romanNumerals.toSymbols(19) shouldBe "XIX"
        romanNumerals.toSymbols(20) shouldBe "XX"
    }

    should("print triky roman numerals") {
        romanNumerals.toSymbols(48) shouldBe "XLVIII"
        romanNumerals.toSymbols(49) shouldBe "XLIX"
        romanNumerals.toSymbols(50) shouldBe "L"
        romanNumerals.toSymbols(51) shouldBe "LI"
        romanNumerals.toSymbols(98) shouldBe "XCVIII"
        romanNumerals.toSymbols(99) shouldBe "XCIX"
        romanNumerals.toSymbols(494) shouldBe "CDXCIV"
        romanNumerals.toSymbols(499) shouldBe "CDXCIX"
        romanNumerals.toSymbols(582) shouldBe "DLXXXII"
        romanNumerals.toSymbols(592) shouldBe "DXCII"
        romanNumerals.toSymbols(999) shouldBe "CMXCIX"
    }

    should("print high roman numerals") {
        romanNumerals.toSymbols(50) shouldBe "L"
        romanNumerals.toSymbols(100) shouldBe "C"
        romanNumerals.toSymbols(200) shouldBe "CC"
        romanNumerals.toSymbols(300) shouldBe "CCC"
        romanNumerals.toSymbols(400) shouldBe "CD"
        romanNumerals.toSymbols(500) shouldBe "D"
        romanNumerals.toSymbols(600) shouldBe "DC"
        romanNumerals.toSymbols(1000) shouldBe "M"
        romanNumerals.toSymbols(2000) shouldBe "MM"
        romanNumerals.toSymbols(3000) shouldBe "MMM"
    }
})
