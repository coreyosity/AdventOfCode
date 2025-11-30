package aoc.y2024

import aoc.common.Utils.println
import aoc.common.Utils.readInput

fun main() {

    fun countX(input: List<MutableList<String>>): Int {
        var count = 0
        input.forEach { list ->
            list.forEach { cell ->
                if (cell == "X") count++
            }
        }
        return count
    }

    fun traverse(input: List<MutableList<String>>, y: Int, x: Int, marker: Direction): Int {
        var (row, cell) = y to x
        var direction = marker
        while (true) {
            if (direction == Direction.UP) {
                if (row - 1 < 0) {
                    input[row][cell] = "X"
                    break
                }
                if (input[row - 1][cell] == "#") {
                    direction = direction.rotateRight()
                }
                if (input[row - 1][cell] == "." || input[row - 1][cell] == "X") {
                    input[row][cell] = "X"
                    row -= 1
                    input[row][cell] = direction.symbol
                }
            } else if (direction == Direction.RIGHT) {
                if (cell + 1 >= input[row].size) {
                    input[row][cell] = "X"
                    break
                }
                if (input[row][cell + 1] == "#") {
                    direction = direction.rotateRight()
                }
                if (input[row][cell + 1] == "." || input[row][cell + 1] == "X") {
                    input[row][cell] = "X"
                    cell += 1
                    input[row][cell] = direction.symbol
                }
            } else if (direction == Direction.DOWN) {
                if (row + 1 >= input.size) {
                    input[row][cell] = "X"
                    break
                }
                if (input[row + 1][cell] == "#") {
                    direction = direction.rotateRight()
                }
                if (input[row + 1][cell] == "." || input[row + 1][cell] == "X") {
                    input[row][cell] = "X"
                    row += 1
                    input[row][cell] = direction.symbol
                }
            } else if (direction == Direction.LEFT) {
                if (cell - 1 < 0) {
                    input[row][cell] = "X"
                    break
                }
                if (input[row][cell - 1] == "#") {
                    direction = direction.rotateRight()
                }
                if (input[row][cell - 1] == "." || input[row][cell - 1] == "X") {
                    input[row][cell] = "X"
                    cell -= 1
                    input[row][cell] = direction.symbol
                }
            }
        }
        return countX(input)
    }

    fun part1(input: List<MutableList<String>>): Int {
        var direction: Direction = Direction.UP
        var (row, cell) = 0 to 0
        input.forEachIndexed { y, list ->
            list.forEachIndexed { x, position ->
                if (position == "^") {
                    direction = Direction.UP
                    row = y
                    cell = x
                } else if (position == ">") {
                    direction = Direction.RIGHT
                    row = y
                    cell = x
                } else if (position == "v") {
                    direction = Direction.DOWN
                    row = y
                    cell = x
                } else if (position == "<") {
                    direction = Direction.LEFT
                    row = y
                    cell = x
                }
            }
        }

        return traverse(input, row, cell, direction)
    }

    val testInput = readInput(2024, "test/Day06P1")
    var testInputList = testInput.map { it.toList().map { it.toString() }.toMutableList() }
    check(part1(testInputList) == 41)
    val input = readInput(2024, "Day06")
    part1(input.map { it.toList().map { it.toString() }.toMutableList() }).println()
}

enum class Direction(val symbol: String) {
    UP("^"),
    RIGHT(">"),
    DOWN("v"),
    LEFT("<");

    fun rotateRight(): Direction {
        return when (this) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
        }
    }
}
