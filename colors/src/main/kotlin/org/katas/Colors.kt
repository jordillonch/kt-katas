package org.katas

@JvmInline
value class Color(private val value: Int) {
    fun red() = value shr 16
    fun green() = 0x00FF00 and value shr 8
    fun blue() = 0x0000FF and value
}

class Colors {
    fun nearest(color: Color): Color = when {
        (color.red() > color.green()) && (color.red() > color.blue()) -> Color(0xFF0000)
        color.green() > color.blue() -> Color(0x00FF00)
        else -> Color(0x0000FF)
    }

    fun farthest(color: Color): Color = when {
        (color.red() < color.green()) && (color.red() < color.blue()) -> Color(0xFF0000)
        color.green() < color.blue() -> Color(0x00FF00)
        else -> Color(0x0000FF)
    }
}
