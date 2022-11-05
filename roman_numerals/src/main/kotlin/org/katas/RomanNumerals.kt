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
        .fold(Pair(number, "")) { (rest, result), (romanNumeralInt, romanNumeral) ->
            calculateRomanNumeralsUntil3Digits(rest, romanNumeralInt, romanNumeral)
                .let { (rest, romanResult) ->
                    calculateRomanNumeralsWhenSubtractingOne(romanResult, rest, romanNumeralInt, romanNumeral)
                }.let { (rest, romanResult) ->
                    Pair(rest, result + romanResult)
                }
        }.second
}

private fun calculateRomanNumeralsUntil3Digits(
    number: Int,
    romanNumeralInt: Int,
    romanNumeral: String
): Pair<Int, String> {
    val timesOfRomanUnits = number / romanNumeralInt
    return Pair(number - romanNumeralInt * timesOfRomanUnits, romanNumeral.repeat(timesOfRomanUnits))
}

private fun calculateRomanNumeralsWhenSubtractingOne(
    result: String,
    rest: Int,
    romanNumeralInt: Int,
    romanNumeral: String
) =
    if (rest != 0 && romanNumeralInt - 1 == rest) {
        Pair(rest - romanNumeralInt + 1, result + "I" + romanNumeral)
    } else {
        Pair(rest, result)
    }
