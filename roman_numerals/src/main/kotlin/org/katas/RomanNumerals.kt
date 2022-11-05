package org.katas

private data class RomanNumeral(val number: Int, val numeral: String)

fun romanNumerals(number: Int) =
    listOf(
        RomanNumeral(1000, "M"),
        RomanNumeral(500, "D"),
        RomanNumeral(100, "C"),
        RomanNumeral(50, "L"),
        RomanNumeral(10, "X"),
        RomanNumeral(5, "V"),
        RomanNumeral(1, "I")
    ).fold(RomanNumeralsIterator(number, "")) { (rest, result), romanNumeral ->
        calculateRomanNumeralsUntil3Digits(rest, romanNumeral).let { (rest, romanResult) ->
            calculateRomanNumeralsWhenSubtractingOne(romanResult, rest, romanNumeral)
        }.let { (rest, romanResult) -> RomanNumeralsIterator(rest, result + romanResult) }
    }.result

private data class RomanNumeralsIterator(val rest: Int, val result: String)

private fun calculateRomanNumeralsUntil3Digits(number: Int, romanNumeral: RomanNumeral) =
    RomanNumeralsIterator(
        number - romanNumeral.number * (number / romanNumeral.number),
        romanNumeral.numeral.repeat(number / romanNumeral.number)
    )

private fun calculateRomanNumeralsWhenSubtractingOne(result: String, rest: Int, romanNumeral: RomanNumeral) =
    if (rest != 0 && romanNumeral.number - 1 == rest) {
        RomanNumeralsIterator(rest - romanNumeral.number + 1, result + "I" + romanNumeral.numeral)
    } else {
        RomanNumeralsIterator(rest, result)
    }
