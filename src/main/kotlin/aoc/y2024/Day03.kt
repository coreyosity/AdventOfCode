package aoc.y2024

import aoc.common.Utils.println
import aoc.common.Utils.readInput

fun main() {

    val mulRegex = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")
    val mulDoDontRegex = Regex("""mul\((\d{1,3}),(\d{1,3})\)|do\(\)|don't\(\)""")

    fun parseInputP1(input: List<String>) = input.flatMap { string ->
        mulRegex.findAll(string)
            .map { matchResult: MatchResult ->
                val (x, y) = matchResult.destructured
                x.toInt() to y.toInt()
            }
    }

    fun parseInputP2(input: List<String>): List<Pair<Int, Int>> {
        var enabled = true
        val result = mutableListOf<Pair<Int, Int>>()

        for (row in input) {
            mulDoDontRegex.findAll(row).forEach { matchResult ->
                when (matchResult.value) {
                    "do()" -> enabled = true
                    "don't()" -> enabled = false
                }
                if (enabled && matchResult.value.contains("mul")) {
                    val (x, y) = matchResult.destructured
                    result.add(x.toInt() to y.toInt())
                } else {
                    result.add(0 to 0)
                }
            }
        }
        return result
    }

    fun part1(input: List<String>): Int {
        var count = 0
        val commands = parseInputP1(input)
        commands.forEach { count += it.first * it.second }
        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        val commands = parseInputP2(input)
        println(commands)
        commands.forEach { count += it.first * it.second }
        return count
    }

    val testInput = readInput(2024, "test/Day03P1")
    check(part1(testInput) == 161)
    val input = readInput(2024, "Day03")
    part1(input).println()
    part2(input).println()
}
