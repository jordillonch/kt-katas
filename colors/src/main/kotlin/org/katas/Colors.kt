package org.katas

@JvmInline
value class Color(private val value: Int) {
    fun red() = value shr 16
    fun green() = 0x00FF00 and value shr 8
    fun blue() = 0x0000FF and value
    fun rgb() = listOf(
        ColorPalette.RED.color to red(),
        ColorPalette.GREEN.color to green(),
        ColorPalette.BLUE.color to blue()
    )
}

enum class ColorPalette(val color: Color) {
    RED(Color(0xFF0000)),
    GREEN(Color(0x00FF00)),
    BLUE(Color(0x0000FF))
}

class Colors {
    fun nearest(color: Color): List<Color> {
        val intensityToSearch = color.rgb().maxBy { it.second }.second
        return color.rgb().filter { it.second == intensityToSearch }.map { it.first }
    }

    fun farthest(color: Color): List<Color> {
        val intensityToSearch = color.rgb().minBy { it.second }.second
        return color.rgb().filter { it.second == intensityToSearch }.map { it.first }
    }
}
