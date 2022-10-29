package org.katas

@JvmInline
value class Color(private val value: Int) {
    fun redComponent(): Int = value shr 16
    fun greenComponent(): Int = 0x00FF00 and value shr 8
    fun blueComponent(): Int = 0x0000FF and value
}

class Colors {
    fun nearest(color: Color): Color {
        return when {
            (color.redComponent() > color.greenComponent()) and
                    (color.redComponent() > color.blueComponent()) -> Color(0xFF0000)
            color.greenComponent() > color.blueComponent() -> Color(0x00FF00)
            else -> Color(0x0000FF)
        }
    }
    fun farthest(color: Color): Color {
        return when {
            (color.redComponent() < color.greenComponent()) and
                    (color.redComponent() < color.blueComponent()) -> Color(0xFF0000)
            color.greenComponent() < color.blueComponent() -> Color(0x00FF00)
            else -> Color(0x0000FF)
        }
    }
}
