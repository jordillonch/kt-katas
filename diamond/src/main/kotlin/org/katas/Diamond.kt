package org.katas

data class DiamondRow(
    val letter: Char,
    val index: Int,
    val firstIndent: Int,
    val spaceBetweenLetters: Int,
    val repeatLetter: Boolean
) {
    companion object {
        fun new(index: Int, currentLetter: Char, maxLetter: Char) = DiamondRow(
            letter = currentLetter,
            index = index,
            firstIndent = calculateFirstIndent(index, calculateMaxIndex(maxLetter)),
            spaceBetweenLetters = calculateSpaceBetweenLetters(index),
            repeatLetter = calculateRepeatLetter(index)
        )

        private fun calculateRepeatLetter(index: Int): Boolean {
            return index > 0
        }

        private fun calculateSpaceBetweenLetters(index: Int): Int {
            return if (index == 0) 0 else index * 2 - 1
        }

        private fun calculateFirstIndent(index: Int, maxIndex: Int): Int {
            return maxIndex - index
        }

        private fun calculateMaxIndex(maxLetter: Char): Int =
            ('A'..maxLetter).mapIndexed { index, _ -> index }.last()
    }

    override fun toString(): String {
        return " ".repeat(firstIndent) +
                letter.toString() +
                " ".repeat(spaceBetweenLetters) +
                if (repeatLetter) letter.toString() else ""
    }
}

fun printDiamond(letter: Char): String {
    val firstDiamondPart = ('A'..letter)
        .mapIndexed() { index, currentLetter -> DiamondRow.new(index, currentLetter, letter) }
    val secondDiamondPart = firstDiamondPart.take(firstDiamondPart.size - 1).reversed()
    return (firstDiamondPart + secondDiamondPart).joinToString("\n")
}

// maxIndex = 2
//index indent0 spacing repeat_letter
//0     2       0       0              |  A
//1     1       1       1              | B B
//2     0       3       1              |C   C
//1     1       1       1              | B B
//0     2       0       0              |  A

// maxIndex = 3
//index indent0 spacing repeat_letter
//0     3       0       0              |   A
//1     2       1       1              |  B B
//2     1       3       1              | C   C
//3     0       5       1              |D     D
//2     1       3       1              | C   C
//1     2       1       1              |  B B
//0     3       0       0              |   A
