package org.katas

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class DiamondTest : ShouldSpec({
    should("print diamond with letter C") {
        printDiamond('C') shouldBe """  A
 B B
C   C
 B B
  A"""
    }

    should("print diamond with letter D") {
        printDiamond('D') shouldBe """   A
  B B
 C   C
D     D
 C   C
  B B
   A"""
    }
})
