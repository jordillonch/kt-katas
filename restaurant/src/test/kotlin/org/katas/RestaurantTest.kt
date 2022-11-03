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

    should("locate a table for 1 person") {
        val table1 = Table(1)
        val table2 = Table(2)
        val manager = SeatingManager(listOf(table2, table1))
        val group = CustomerGroup(1)
        manager.arrives(group)
        manager.locate(group) shouldBe table1
        manager.leaves(group)
    }

    should("locate a table for 1 and for 2 people") {
        val table1 = Table(1)
        val table2 = Table(2)
        val table3 = Table(3)
        val manager = SeatingManager(listOf(table1, table2, table3))
        val group1 = CustomerGroup(1)
        val group2 = CustomerGroup(2)
        manager.arrives(group1)
        manager.arrives(group2)
        manager.locate(group1) shouldBe table1
        manager.locate(group2) shouldBe table2
        manager.leaves(group1)
        manager.locate(group1) shouldBe null
        manager.locate(group2) shouldBe table2
        manager.leaves(group2)
        manager.locate(group1) shouldBe null
        manager.locate(group2) shouldBe null
    }

    should("locate a shared table") {
        val table1 = Table(2)
        val manager = SeatingManager(listOf(table1))
        val group1 = CustomerGroup(1)
        val group2 = CustomerGroup(1)
        manager.arrives(group1)
        manager.arrives(group2)
        manager.locate(group1) shouldBe table1
        manager.locate(group2) shouldBe table1
        manager.leaves(group1)
        manager.locate(group1) shouldBe null
        manager.locate(group2) shouldBe table1
        manager.leaves(group2)
        manager.locate(group1) shouldBe null
        manager.locate(group2) shouldBe null
    }

    should("wait for a table until a group leaves") {
        val table1 = Table(6)
        val manager = SeatingManager(listOf(table1))
        val group1 = CustomerGroup(2)
        val group2 = CustomerGroup(6)
        manager.arrives(group1)
        manager.arrives(group2)
        manager.locate(group1) shouldBe table1
        manager.locate(group2) shouldBe null
        manager.leaves(group1)
        manager.locate(group1) shouldBe null
        manager.locate(group2) shouldBe table1
        manager.leaves(group2)
        manager.locate(group1) shouldBe null
        manager.locate(group2) shouldBe null
    }
})
