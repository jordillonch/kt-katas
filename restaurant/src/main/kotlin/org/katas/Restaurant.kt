package org.katas

import java.util.UUID

// size: number of chairs around this table
data class Table(val size: Int = 0, val id: UUID = UUID.randomUUID())

// size: number of people in the group
data class CustomerGroup(val size: Int = 0, val id: UUID = UUID.randomUUID())

data class TableState(val table: Table, val groups: MutableList<CustomerGroup>, val id: UUID = UUID.randomUUID())

class SeatingManager(tables: List<Table>) {
    private val waitingList: MutableList<CustomerGroup> = mutableListOf()
    private val tablesState: MutableList<TableState> = tables.sortedBy { it.size }
        .map { TableState(it, mutableListOf()) }.toMutableList()

    /*
    * Group arrives and wants to be seated.
    * Complexity: 3 * O(nTables). It is 3 times because in the worst case we should search for a whole free table and
    *             if any is found, we should search for a shared table and then we should set the table as occupied.
    *
    *             nTables = the number of tables
    */
    fun arrives(group: CustomerGroup) {
        if (seat(group) == null) {
            waitingList.add(group)
        }
    }

    /*
    * Whether seated or not, the group leaves the restaurant.
    * Complexity: 2 * O(nTables) * O(nGroups) + O(nWaitingList) + O(nWaitingList)
    *
    *             locate complexity: O(nTables) * O(nGroups)
    *             freeTable complexity: O(nTables) * O(nGroups)
    *             tryToLocateTableForWaitingList: O(nWaitingList) + O(nWaitingList)
    *
    *             nTables = the number of tables
    *             nGroups = worst case of number of groups seated in a table
    *             nWaitingList = the number of groups waiting to be seated
    */
    fun leaves(group: CustomerGroup) {
        val tableToFree = locate(group)
        if (tableToFree != null) {
            freeTable(tableToFree, group)
            tryToLocateTableForWaitingList()
        } else {
            waitingList.remove(group)
        }
    }

    /*
    * Return the table at which the group is seated, or null if they are not seated (whether they're waiting or
    * already left).
    * Complexity: O(nTables) * O(nGroups)
    *
    *             nTables = the number of tables
    *             nGroups = worst case of number of groups seated in a table
    */
    fun locate(group: CustomerGroup): Table? = tablesState.find { it.groups.contains(group) }?.table

    /*
    * Complexity: 3 * O(nTables)
    *
    *             locateFreeWholeTable complexity = O(nTables)
    *             locateFreeSharedTable complexity = O(nTables)
    *             occupyTable complexity = O(nTables)
    *
    *             nTables = the number of tables
    */
    private fun seat(group: CustomerGroup): Table? {
        val freeWholeTable = locateFreeWholeTable(group)
        if (freeWholeTable != null) {
            occupyTable(freeWholeTable, group)
            return freeWholeTable
        } else {
            val freeSharedTable = locateFreeSharedTable(group)
            if (freeSharedTable != null) {
                occupyTable(freeSharedTable, group)
                return freeSharedTable
            }
        }
        return null
    }

    /*
    * Complexity: O(nTables) * O(nGroups)
    *
    *             nTables = the number of tables
    *             nGroups = worst case of number of groups seated in a table
    */
    private fun freeTable(table: Table, group: CustomerGroup) {
        tablesState.find { it.table == table }!!.groups.remove(group)
    }

    /*
    * Complexity: O(nWaitingList) + O(nWaitingList)
    *
    *             nWaitingList = the number of groups waiting to be seated
    */
    private fun tryToLocateTableForWaitingList() {
        waitingList
            .filter { seat(it) != null }
            .forEach { waitingList.remove(it) }
    }

    /*
    * Complexity: O(nTables)
    *
    *             nTables = the number of tables
    */
    private fun locateFreeWholeTable(group: CustomerGroup): Table? =
        tablesState.find { it.groups.size == 0 && it.table.size >= group.size }?.table

    /*
    * Complexity: O(nTables)
    *
     *            nTables = the number of tables
    */
    private fun locateFreeSharedTable(group: CustomerGroup): Table? {
        tablesState
            .filter { it.groups.size > 0 && it.table.size >= group.size }
            .forEach { tableState ->
                val freeSeats = tableState.table.size - tableState.groups.sumOf { it.size }
                if (freeSeats >= group.size) {
                    return tableState.table
                }
            }
        return null
    }

    /*
    * Complexity: O(nTables)
    *
    *             nTables = the number of tables
    */
    private fun occupyTable(table: Table, group: CustomerGroup) {
        tablesState.find { it.table == table }!!.groups.add(group)
    }
}
