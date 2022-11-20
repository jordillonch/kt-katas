package org.katas

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlaygroundTest : StringSpec({
    "foo" {
        play() shouldBe true
    }
})
