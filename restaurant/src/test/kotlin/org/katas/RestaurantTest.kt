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
        val manager = SeatingManager((1..5).map { Table(it) })
        val group = CustomerGroup(1)
        manager.arrives(group)
        manager.locate(CustomerGroup(1)) shouldBe Table(1)
        manager.leaves(group)
    }
})
