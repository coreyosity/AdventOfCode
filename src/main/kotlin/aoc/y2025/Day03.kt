package aoc.y2025

import aoc.common.Utils.readInput

fun main() {
    val p1TestResult: Long = 357
    val p2TestResult = 3121910778619

    fun getMaxValues(row: List<Int>, remaining: Int): List<Int> {
        if (remaining == 0) { return emptyList() }

        val window = row.size - remaining + 1
        val maxValue = row.take(window).maxOrNull()
        val position = row.indexOf(maxValue)
        val newRow = row.drop(position + 1)

        return listOf(maxValue!!) + getMaxValues(newRow, remaining - 1)
    }

    fun part1(input: List<String>): Long {
        val numbers = input.map { row -> row.chunked(1).map{ it.toInt()} }
        val sum = numbers.fold(0.toLong()) { acc, row ->
            val joltage = getMaxValues(row, 2).joinToString("").toLong()
            acc + joltage
        }
        return sum
    }

    fun part2(input: List<String>): Long {
        val numbers = input.map { row -> row.chunked(1).map{ it.toInt()} }
        val sum = numbers.fold(0.toLong()) { acc, row ->
            val joltage = getMaxValues(row, 12).joinToString("").toLong()
            acc + joltage
        }
        return sum
    }



    val testInput = readInput(2025,"test/Day03P1")
    val input = readInput(2025,"Day03")

    check(part1(testInput) == p1TestResult)
    println(part1(input))
    check(part2(testInput) == p2TestResult)
    println(part2(input))
}