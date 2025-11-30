package aoc.y2024

import aoc.common.Utils.println
import aoc.common.Utils.readInput
import kotlin.math.abs

fun main() {
    var count: Int
    fun validateRow(row: List<Int>): Boolean {
        val isAscending = row.zipWithNext().all { (a, b) -> a < b }
        val isDescending = row.zipWithNext().all { (a, b) -> a > b }

        if (!isAscending && !isDescending) return false

        return row.zipWithNext().all { (a, b) -> abs(a - b) <= 3 }
    }

    fun validateRowWithLevelRemoval(row: List<Int>): Boolean {
        if (validateRow(row)) return true

        for (i in row.indices) {
            val modifiedList = row.toMutableList().apply { removeAt(i) }
            if (validateRow(modifiedList)) return true
        }
        return false
    }

    fun part1(input: List<String>): Int {
        count = 0
        val rows = input.map { it.split(" ") }.map { row -> row.map { num -> num.toInt() } }
        rows.forEach { row ->
            if (validateRow(row)) {
                count++
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        count = 0
        val rows = input.map { it.split(" ") }.map { row -> row.map { num -> num.toInt() } }
        rows.forEach { row ->
            if (validateRowWithLevelRemoval(row)) {
                count++
            }
        }
        return count
    }

    val testInput = readInput(2024, "test/Day02P1")
    check(part1(testInput) == 2)
    val input = readInput(2024, "Day02")
    part1(input).println()
    check(part2(testInput) == 4)
    part2(input).println()
}
