package org.katas

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ColorsTest : FunSpec({
    val colors = Colors()

    listOf(Color(0xFF0000), Color(0x221100), Color(0xFFFEFD)).forEach {
        test("$it should be nearest to red") {
            colors.nearest(it) shouldBe Color(0xFF0000)
        }
    }
    listOf(Color(0x00FF00), Color(0x112200), Color(0xFEFFFD)).forEach {
        test("$it should be nearest to green") {
            colors.nearest(it) shouldBe Color(0x00FF00)
        }
    }
    listOf(Color(0x0000FF), Color(0x001122), Color(0xFEFDFF)).forEach {
        test("$it should be nearest to blue") {
            colors.nearest(it) shouldBe Color(0x0000FF)
        }
    }

    listOf(Color(0x0001FF), Color(0x0203F4), Color(0xF2F3F4)).forEach {
        test("$it should be farthest to red") {
            colors.farthest(it) shouldBe Color(0xFF0000)
        }
    }
    listOf(Color(0x0100FF), Color(0xF40203), Color(0xF4F2F3)).forEach {
        test("$it should be farthest to green") {
            colors.farthest(it) shouldBe Color(0x00FF00)
        }
    }
    listOf(Color(0xFF0100), Color(0x03F402), Color(0xF3F4F2)).forEach {
        test("$it should be farthest to green") {
            colors.farthest(it) shouldBe Color(0x0000FF)
        }
    }
})
