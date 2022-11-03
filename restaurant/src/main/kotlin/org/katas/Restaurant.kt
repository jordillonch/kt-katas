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

    /* Group arrives and wants to be seated. */
    fun arrives(group: CustomerGroup) {
        val locatedTable = seat(group)
        if (locatedTable == null) {
            waitingList.add(group)
        }
    }

    /* Whether seated or not, the group leaves the restaurant. */
    fun leaves(group: CustomerGroup) {
        val tableToFree = locate(group)
        if (tableToFree != null) {
            freeTable(tableToFree, group)
            tryToLocateTableForWaitingList()
        } else {
            waitingList.remove(group)
        }
    }

    /* Return the table at which the group is seated, or null if
    they are not seated (whether they're waiting or already left). */
    fun locate(group: CustomerGroup): Table? = tablesState.find { it.groups.contains(group) }?.table

    private fun seat(group: CustomerGroup): Table? {
        val freeWholeTable = locateFreeWholeTable(group)
        if (freeWholeTable != null) {
            useTable(freeWholeTable, group)
            return freeWholeTable
        } else {
            val freeSharedTable = locateFreeSharedTable(group)
            if (freeSharedTable != null) {
                useTable(freeSharedTable, group)
                return freeSharedTable
            }
        }
        return null
    }

    private fun freeTable(table: Table, group: CustomerGroup) {
        tablesState.find { it.table == table }!!.groups.remove(group)
    }

    private fun tryToLocateTableForWaitingList() {
        waitingList
            .filter { seat(it) != null }
            .forEach { waitingList.remove(it) }
    }

    private fun locateFreeWholeTable(group: CustomerGroup): Table? =
        tablesState.find { it.groups.size == 0 && it.table.size >= group.size }?.table

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

    private fun useTable(table: Table, group: CustomerGroup) {
        tablesState.find { it.table == table }!!.groups.add(group)
    }
}
