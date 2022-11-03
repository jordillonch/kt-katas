package org.katas

import java.util.UUID

// size: number of chairs around this table
data class Table(val size: Int = 0, val id: UUID = UUID.randomUUID())

// size: number of people in the group
data class CustomerGroup(val size: Int = 0, val id: UUID = UUID.randomUUID())

data class TableState(
    val table: Table,
    val groups: MutableList<CustomerGroup>
) {
    val id: UUID = UUID.randomUUID()
    var seatedPeople: Int = 0

    fun occupy(group: CustomerGroup): TableState {
        groups.add(group)
        calculateSeatedPeople()
        return this
    }

    fun free(group: CustomerGroup): TableState {
        groups.remove(group)
        calculateSeatedPeople()
        return this
    }

    fun isThereSpaceFor(group: CustomerGroup) = table.size - seatedPeople >= group.size

    private fun calculateSeatedPeople() {
        seatedPeople = groups.sumOf { it.size }
    }
}

class SeatingManager(tables: List<Table>) {
    private val waitingList: MutableList<CustomerGroup> = mutableListOf()
    private val tablesState: MutableList<TableState> = tables.sortedBy { it.size }
        .map { TableState(it, mutableListOf()) }.toMutableList()

    /*
    * Group arrives and wants to be seated.
    * Complexity: 2 * O(nTables)
    *             nTables = the number of tables
    */
    fun arrives(group: CustomerGroup) {
        if (seat(group) == null) {
            waitingList.add(group)
        }
    }

    /*
    * Whether seated or not, the group leaves the restaurant.
    * Complexity: O(nTables) * O(nGroupsInTable) + O(nGroupsInTable) + 2 * O(nTables) + O(nWaitingList)
    *             nTables = the number of tables
    *             nGroupsInTable = number of groups seated in a table
    *             nWaitingList = the number of groups waiting to be seated
    *
    *             locateTableState: O(nTables) * O(nGroupsInTable)
    *             locateTableState.free: O(nGroupsInTable)
    *             tryToLocateTableForWaitingList: 2 * O(nTables) + O(nWaitingList)
    */
    fun leaves(group: CustomerGroup) {
        locateTableState(group)?.free(group)
        waitingList.remove(group)
        tryToLocateTableForWaitingList()
    }

    /*
    * Return the table at which the group is seated, or null if they are not seated (whether they're waiting or
    * already left).
    * Complexity: O(nTables) * O(nGroupsInTable)
    *             nTables = the number of tables
    *             nGroupsInTable = number of groups seated in a table
    */
    fun locate(group: CustomerGroup): Table? = locateTableState(group)?.table

    /*
    * Complexity: O(nTables) * O(nGroupsInTable)
    *             nTables = the number of tables
    *             nGroupsInTable = number of groups seated in a table
    */
    private fun locateTableState(group: CustomerGroup) = tablesState.find { it.groups.contains(group) }

    /*
    * Complexity: 2 * O(nTables)
    *             nTables = the number of tables
    *             nGroupsInTable = number of groups seated in a table
    *
    *             locateFreeWholeTable = O(nTables)
    *             locateFreeSharedTable = O(nTables)
    */
    private fun seat(group: CustomerGroup): Table? {
        locateFreeWholeTable(group)?.occupy(group)?.let { return it.table }
        locateFreeSharedTable(group)?.occupy(group)?.let { return it.table }
        return null
    }

    /*
    * Complexity: 2 * O(nTables) + O(nWaitingList)
    *             nWaitingList = the number of groups waiting to be seated
    */
    private fun tryToLocateTableForWaitingList() {
        waitingList
            .filter { seat(it) != null }
            .forEach { waitingList.remove(it) }
    }

    /*
    * Complexity: O(nTables)
    *             nTables = the number of tables
    */
    private fun locateFreeWholeTable(group: CustomerGroup): TableState? =
        tablesState.find { it.groups.size == 0 && it.table.size >= group.size }

    /*
    * Complexity: O(nTables)
    *             nTables = the number of tables
    */
    private fun locateFreeSharedTable(group: CustomerGroup): TableState? =
        tablesState.firstOrNull { it.isThereSpaceFor(group) }
}
