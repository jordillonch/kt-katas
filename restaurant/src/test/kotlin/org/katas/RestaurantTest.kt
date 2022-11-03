package org.katas

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class RestaurantTest : ShouldSpec({

    should("not locate any table if seating manager is empty") {
        val manager = SeatingManager(emptyList())
        val group = CustomerGroup(1)
        manager.arrives(group)
        manager.locate(group) shouldBe null
        manager.leaves(group)
    }

    should("locate a table of 1 if seating manager initialized with tables") {
        val table1 = Table(1)
        val table2 = Table(2)
        val table3 = Table(3)
        val manager = SeatingManager(listOf(table1, table2, table3))
        val group = CustomerGroup(1)
        manager.arrives(group)
        manager.locate(group) shouldBe table1
        manager.leaves(group)
    }
})
