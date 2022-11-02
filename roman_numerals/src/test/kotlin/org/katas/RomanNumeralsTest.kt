package org.katas

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class RomanNumeralsTest : ShouldSpec({
    should("print roman numerals") {
        romanNumerals(1) shouldBe "I"
        romanNumerals(10) shouldBe "X"
        romanNumerals(7) shouldBe "VII"
    }
})
