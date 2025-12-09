package aoc.common

data class Point(val row: Int, val col: Int) {
    operator fun plus(direction: Direction) = Point(row + direction.dRow, col + direction.dCol)
}

enum class Direction(val dRow: Int, val dCol: Int) {
    NORTH(-1, 0),
    SOUTH(1, 0),
    EAST(0, 1),
    WEST(0, -1),
    NORTH_EAST(-1, 1),
    NORTH_WEST(-1, -1),
    SOUTH_EAST(1, 1),
    SOUTH_WEST(1, -1);

    companion object {
        val cardinal = listOf(NORTH, SOUTH, EAST, WEST)
        val all = entries
    }
}

data class Grid(val cells: List<List<Char>>) : Iterable<Point> {
    val rows: Int = cells.size
    val cols: Int = cells.firstOrNull()?.size ?: 0

    fun get(point: Point): Char? =
        if (inBounds(point)) cells[point.row][point.col] else null

    fun inBounds(point: Point): Boolean =
        point.row in 0 until rows && point.col in 0 until cols

    fun getAdjacent(point: Point): List<Point> =
        Direction.all.map { point + it }.filter { inBounds(it) }

    fun findAll(char: Char): List<Point> = buildList {
        cells.forEachIndexed { row, line ->
            line.forEachIndexed { col, c ->
                if (c == char) add(Point(row, col))
            }
        }
    }

    fun set(point: Point, value: Char): Grid {
        val newCells = cells.mapIndexed { row, line ->
            if (row == point.row) {
                line.mapIndexed { col, c -> if (col == point.col) value else c }
            } else line
        }
        return Grid(newCells)
    }

    fun copy(modifications: Map<Point, Char>): Grid {
        val newCells = cells.mapIndexed { row, line ->
            line.mapIndexed { col, c ->
                modifications[Point(row, col)] ?: c
            }
        }
        return Grid(newCells)
    }

    override fun iterator(): Iterator<Point> = iterator {
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                yield(Point(row, col))
            }
        }
    }
}
