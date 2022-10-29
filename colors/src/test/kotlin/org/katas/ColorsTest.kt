package org.katas

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ColorsTest : FunSpec({
    val colors = Colors()

    test("Colors should be nearest to red") {
        colors.nearest(Color(0xFF0000)) shouldBe Color(0xFF0000)
        colors.nearest(Color(0xF40203)) shouldBe Color(0xFF0000)
        colors.nearest(Color(0xF4F2F3)) shouldBe Color(0xFF0000)
    }
    test("Colors should be nearest to green") {
        colors.nearest(Color(0x00FF00)) shouldBe Color(0x00FF00)
        colors.nearest(Color(0x02F403)) shouldBe Color(0x00FF00)
        colors.nearest(Color(0xF2F4F3)) shouldBe Color(0x00FF00)
    }
    test("Colors should be nearest to blue") {
        colors.nearest(Color(0x0000FF)) shouldBe Color(0x0000FF)
        colors.nearest(Color(0x0203F4)) shouldBe Color(0x0000FF)
        colors.nearest(Color(0xF2F3F4)) shouldBe Color(0x0000FF)
    }

    test("Colors should be farthest to red") {
        colors.farthest(Color(0x0001FF)) shouldBe Color(0xFF0000)
        colors.farthest(Color(0x0203F4)) shouldBe Color(0xFF0000)
        colors.farthest(Color(0xF2F3F4)) shouldBe Color(0xFF0000)
    }
    test("Colors should be farthest to green") {
        colors.farthest(Color(0x0100FF)) shouldBe Color(0x00FF00)
        colors.farthest(Color(0xF40203)) shouldBe Color(0x00FF00)
        colors.farthest(Color(0xF4F2F3)) shouldBe Color(0x00FF00)
    }
    test("Colors should be farthest to blue") {
        colors.farthest(Color(0xFF0100)) shouldBe Color(0x0000FF)
        colors.farthest(Color(0x03F402)) shouldBe Color(0x0000FF)
        colors.farthest(Color(0xF3F4F2)) shouldBe Color(0x0000FF)
    }
})
