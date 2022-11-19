package org.katas

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DataStructuresTest : StringSpec({
    "hash table" {
        val map = HashTable<String, Int>()
        map.add("this", 1)
        map.add("coder", 2)
        map.add("this", 4)
        map.add("hi", 5)
        map.get("this") shouldBe 4
        map.get("coder") shouldBe 2
        map.get("hi") shouldBe 5
    }
})
