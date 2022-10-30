package org.katas

private const val highestDecimalColorValue = 255

@JvmInline
value class Color(private val hexadecimal: String) {
    private val red: Int
        get() = hexadecimal.substring(1, 3).toInt(16)
    private val green: Int
        get() = hexadecimal.substring(3, 5).toInt(16)
    private val blue: Int
        get() = hexadecimal.substring(5, 7).toInt(16)
    private val sortedColors: Map<Int, List<BasePaletteColor>>
        get() = listOf(
            Pair(BasePaletteColor.RED, highestDecimalColorValue - red),
            Pair(BasePaletteColor.GREEN, highestDecimalColorValue - green),
            Pair(BasePaletteColor.BLUE, highestDecimalColorValue - blue)
        )
            .groupBy { it.second }
            .mapValues { it.value.map { pair -> pair.first } }
            .toSortedMap()

    fun nearestColor(): List<BasePaletteColor> = sortedColors.entries.first().value

    fun farthestColor(): List<BasePaletteColor> = sortedColors.entries.last().value
}
