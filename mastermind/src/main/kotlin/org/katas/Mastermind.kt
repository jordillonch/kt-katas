package org.katas

class Mastermind(private val secret: List<String>) {
    fun evaluate(guess: List<String>): Pair<Int, Int> {
        return Pair(countWellPlaced(guess), countMisplaced(guess))
    }

    private fun countWellPlaced(guess: List<String>): Int {
        return secret.zip(guess).filter { (secretItem, guessItem) -> secretItem == guessItem }.size
    }

    private fun countMisplaced(guess: List<String>): Int {
        return secret.zip(guess).filter { (secretItem, guessItem) -> secretItem != guessItem && guessItem in secret }.size
    }
}
