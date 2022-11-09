package org.katas

class Mastermind(private val secret: List<String>) {
    fun evaluate(guess: List<String>): Pair<Int, Int> {
        return Pair(countWellPlaced(guess), countMisplaced(guess))
    }

    private fun countWellPlaced(guess: List<String>) =
        secret.zip(guess).count { (secretItem, guessItem) -> secretItem == guessItem }

    private fun countMisplaced(guess: List<String>) =
        secret.zip(guess).fold(Pair(secret.toMutableList(), 0)) { (restSecret, accCount), (secretItem, guessItem) ->
            if (secretItem != guessItem && guessItem in restSecret) {
                restSecret.remove(guessItem)
                Pair(restSecret, accCount + 1)
            } else {
                Pair(restSecret, accCount)
            }
        }.second
}
