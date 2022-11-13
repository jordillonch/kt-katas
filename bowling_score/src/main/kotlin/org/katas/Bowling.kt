package org.katas

fun calculate(frames: Frames): Points {
    val framesWithNext = frames.zipWithNext() + Pair(frames.last(), null)
    val framesWithNextAndNext = (framesWithNext.zipWithNext() + Pair(framesWithNext.last(), null))
        .map { FrameWithNextAndNext(it.first.first, it.first.second, it.second?.second) }

    return framesWithNextAndNext.fold(0)
    { acc: Points, frame: FrameWithNextAndNext ->
        when (frame.extra()) {
            ExtraPoints.SPARE -> acc + 10 + frame.nextRoll()
            ExtraPoints.STRIKE -> acc + 10 + frame.nextTwoRolls()
            null -> acc + frame.currentPoints()
        }
    }
}

typealias Knocks = Int
typealias Frame = List<Knocks>
typealias Frames = List<Frame>
typealias Points = Int

enum class ExtraPoints {
    SPARE, STRIKE
}

data class FrameWithNextAndNext(val current: Frame, val next: Frame?, val nextNext: Frame?) {
    fun nextRoll(): Points = next?.first() ?: 0
    fun nextTwoRolls(): Points {
        if (next?.size == 2 && next[0] != 10) return next.sum()
        if (next?.size == 2 && next[0] == 10) return next[0] + (nextNext?.get(0) ?: 0)
        if (next?.size == 3 && next[0] == 10) return next[0] + next[1]
        return 0
    }

    fun extra(): ExtraPoints? =
        if (current.size == 2 && current.first() == 10) ExtraPoints.STRIKE
        else if (current.size == 2 && current.sum() == 10) ExtraPoints.SPARE
        else null

    fun currentPoints() = current.sum()
}
