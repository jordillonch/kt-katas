package org.katas

data class Board(val columns: Int, val rows: Int, val board: List<String>) {
    val mineSymbol = '*'

    fun isEmpty() = rows == 0 && columns == 0

    fun analyze(): List<String> =
        board.mapIndexed() { y, row ->
            row.mapIndexed() { x, _ ->
                if (isMine(x, y)) mineSymbol
                else sumSurroundingMines(x, y).toString()
            }.joinToString("")
        }

    private fun isMine(x: Int, y: Int): Boolean = when {
        isOutOfBoardBoundaries(x, y) -> false
        else -> board[y][x] == mineSymbol
    }

    private fun isOutOfBoardBoundaries(x: Int, y: Int) = (x < 0) or (y < 0) or (x >= columns) or (y >= rows)

    private fun sumSurroundingMines(x: Int, y: Int): Int =
        (x - 1..x + 1).fold(0) { accX, xPos ->
            accX + (y - 1..y + 1).fold(0) { accY, yPos ->
                accY + if (isMine(xPos, yPos)) 1 else 0
            }
        }
}

class Boards(private val input: String) {
    fun toBoards(): List<Board> {
        val inputLines = input.trim().split("\n")
        return inputLines.mapIndexedNotNull() { index, line ->
            val y = line.split(" ")[0].toIntOrNull()
            if (y != null) {
                val x = line.split(" ")[1].toInt()
                Board(x, y, inputLines.subList(index + 1, index + 1 + y))
            } else null
        }
    }
}

class Minesweeper {
    fun play(input: String) =
        Boards(input)
            .toBoards()
            .mapIndexed { index, inputBoard -> analyze(index, inputBoard) }
            .joinToString("\n").trim()

    private fun analyze(index: Int, board: Board) =
        if (board.isEmpty()) ""
        else "Field #${index + 1}:\n" + board.analyze().joinToString("\n") + "\n"
}
