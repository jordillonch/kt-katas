package org.katas

import java.util.UUID

/* size: number of chairs around this table */
data class Table(val size: Int = 0, val id: UUID = UUID.randomUUID())

/* size: number of people in the group */
data class CustomerGroup(val size: Int = 0, val id: UUID = UUID.randomUUID())

data class TableState(val table: Table, val groups: MutableList<CustomerGroup>, val id: UUID = UUID.randomUUID()) {
    private var seatedPeople: Int = 0

    fun occupy(group: CustomerGroup): TableState {
        groups.add(group)
        seatedPeople += group.size
        return this
    }

    fun free(group: CustomerGroup): TableState {
        groups.remove(group)
        seatedPeople -= group.size
        return this
    }

    fun isThereSpaceFor(group: CustomerGroup) = table.size - seatedPeople >= group.size

    fun isEmpty() = seatedPeople == 0
}

class SeatingManager(tables: List<Table>) {
    private val waitingList: MutableList<CustomerGroup> = mutableListOf()
    private val tablesState: MutableList<TableState> = tables.sortedBy { it.size }
        .map { TableState(it, mutableListOf()) }.toMutableList()

    /* Group arrives and wants to be seated.
    * Complexity: 2 * O(numberOfTables)
    * Memory usage: size of TableState + size of CustomerGroup */
    fun arrives(group: CustomerGroup) {
        if (seat(group) == null) {
            waitingList.add(group)
        }
    }

    /* Whether seated or not, the group leaves the restaurant.
    *  Complexity: O(numberOfTables) * O(numberOfGroupsInATable) + O(numberOfGroupsInATable)
    *              + 2 * O(numberOfTables) + O(groupsInTheWaitingList)
    *  Memory usage: decrease in size of TableState + size of CustomerGroup */
    fun leaves(group: CustomerGroup) {
        locateTableState(group)?.free(group)
        waitingList.remove(group)
        tryToLocateTableForWaitingList()
    }

    /* Return the table at which the group is seated, or null if they are not seated (whether they're waiting or
    *  already left).
    *  Complexity: O(numberOfTables) * O(numberOfGroupsInATable)
    *  Memory usage: neither increasing nor decreasing */
    fun locate(group: CustomerGroup): Table? = locateTableState(group)?.table

    /* Complexity: O(numberOfTables) * O(numberOfGroupsInATable) */
    private fun locateTableState(group: CustomerGroup) = tablesState.find { it.groups.contains(group) }

    /* Complexity: 2 * O(numberOfTables) */
    private fun seat(group: CustomerGroup): Table? {
        locateFreeWholeTable(group)?.occupy(group)?.let { return it.table }
        locateFreeSharedTable(group)?.occupy(group)?.let { return it.table }
        return null
    }

    /* Complexity: 2 * O(numberOfTables) + O(groupsInTheWaitingList) */
    private fun tryToLocateTableForWaitingList() {
        waitingList
            .filter { seat(it) != null }
            .forEach { waitingList.remove(it) }
    }

    /* Complexity: O(numberOfTables) */
    private fun locateFreeWholeTable(group: CustomerGroup): TableState? =
        tablesState.find { it.isEmpty() && it.isThereSpaceFor(group) }

    /* Complexity: O(numberOfTables) */
    private fun locateFreeSharedTable(group: CustomerGroup): TableState? =
        tablesState.find { it.isThereSpaceFor(group) }
}
