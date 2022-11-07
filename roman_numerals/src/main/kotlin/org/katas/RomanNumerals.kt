package org.katas

class RomanNumerals {
    private val number1000 = RomanNumeral(1000, "M")
    private val number500 = RomanNumeral(500, "D")
    private val number100 = RomanNumeral(100, "C")
    private val number50 = RomanNumeral(50, "L")
    private val number10 = RomanNumeral(10, "X")
    private val number5 = RomanNumeral(5, "V")
    private val number1 = RomanNumeral(1, "I")
    private val number0 = RomanNumeral(0, "")

    private val romanNumeralConfigurations = listOf(
        RomanNumeralConfiguration(romanNumeral = number1000, canBeSubstractedBy = number100),
        RomanNumeralConfiguration(romanNumeral = number500, canBeSubstractedBy = number100),
        RomanNumeralConfiguration(romanNumeral = number100, canBeSubstractedBy = number10),
        RomanNumeralConfiguration(romanNumeral = number50, canBeSubstractedBy = number10),
        RomanNumeralConfiguration(romanNumeral = number10, canBeSubstractedBy = number1),
        RomanNumeralConfiguration(romanNumeral = number5, canBeSubstractedBy = number1),
        RomanNumeralConfiguration(romanNumeral = number1, canBeSubstractedBy = number0)
    )

    fun toSymbols(number: Int) =
        romanNumeralConfigurations.fold(RomanNumeralsIterator(number, "")) { (rest, result), romanNumeral ->
            romanNumeral.calculateRomanNumeralsUntil3Digits(rest).let { (rest, romanResult) ->
                romanNumeral.calculateRomanNumeralsWhenSubtracting(romanResult, rest)
            }.let { (rest, romanResult) -> RomanNumeralsIterator(rest, result + romanResult) }
        }.result

    private data class RomanNumeralsIterator(val rest: Int, val result: String)

    private data class RomanNumeral(val number: Int, val symbol: String)

    private data class RomanNumeralConfiguration(val romanNumeral: RomanNumeral, val canBeSubstractedBy: RomanNumeral) {
        fun calculateRomanNumeralsUntil3Digits(number: Int): RomanNumeralsIterator {
            val repetitions = number / romanNumeral.number
            return if (repetitions <= 3) {
                RomanNumeralsIterator(
                    number - romanNumeral.number * repetitions,
                    romanNumeral.symbol.repeat(repetitions)
                )
            } else {
                RomanNumeralsIterator(number, "")
            }
        }

        fun calculateRomanNumeralsWhenSubtracting(result: String, rest: Int): RomanNumeralsIterator {
            val numberToSubtract = romanNumeral.number - canBeSubstractedBy.number
            return if (rest != 0 && numberToSubtract == rest - (rest % numberToSubtract)) {
                RomanNumeralsIterator(
                    rest - numberToSubtract,
                    result + canBeSubstractedBy.symbol + romanNumeral.symbol
                )
            } else {
                RomanNumeralsIterator(rest, result)
            }
        }
    }
}
