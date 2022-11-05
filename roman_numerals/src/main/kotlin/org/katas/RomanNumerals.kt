package org.katas

fun romanNumerals(number: Int): String {
    val romanUnits = mapOf(
        1000 to "M",
        500 to "D",
        100 to "C",
        50 to "L",
        10 to "X",
        5 to "V",
        1 to "I"
    )
    return romanUnits.entries
        .fold(RomanNumeralsIterator(number, "")) { (rest, result), (romanNumeralInt, romanNumeral) ->
            calculateRomanNumeralsUntil3Digits(rest, romanNumeralInt, romanNumeral).let { (rest, romanResult) ->
                calculateRomanNumeralsWhenSubtractingOne(romanResult, rest, romanNumeralInt, romanNumeral)
            }.let { (rest, romanResult) -> RomanNumeralsIterator(rest, result + romanResult) }
        }.result
}

private data class RomanNumeralsIterator(val rest: Int, val result: String)

private fun calculateRomanNumeralsUntil3Digits(
    number: Int,
    romanNumeralInt: Int,
    romanNumeral: String
): RomanNumeralsIterator {
    val timesOfRomanUnits = number / romanNumeralInt
    return RomanNumeralsIterator(number - romanNumeralInt * timesOfRomanUnits, romanNumeral.repeat(timesOfRomanUnits))
}

private fun calculateRomanNumeralsWhenSubtractingOne(
    result: String,
    rest: Int,
    romanNumeralInt: Int,
    romanNumeral: String
) =
    if (rest != 0 && romanNumeralInt - 1 == rest) {
        RomanNumeralsIterator(rest - romanNumeralInt + 1, result + "I" + romanNumeral)
    } else {
        RomanNumeralsIterator(rest, result)
    }
