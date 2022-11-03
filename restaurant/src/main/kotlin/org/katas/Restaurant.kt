package org.katas

// size: number of chairs around this table
data class Table(val size: Int = 0)

// size: number of people in the group
data class CustomerGroup(val size: Int = 0)


class SeatingManager(private val tables: List<Table>) {
    /* Group arrives and wants to be seated. */
    fun arrives(group: CustomerGroup) {

    }

    /* Whether seated or not, the group leaves the restaurant. */
    fun leaves(group: CustomerGroup) {

    }

    /* Return the table at which the group is seated, or null if
    they are not seated (whether they're waiting or already left). */
    fun locate(group: CustomerGroup): Table? {
        return null
    }
}
