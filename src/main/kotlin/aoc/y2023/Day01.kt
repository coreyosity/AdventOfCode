package aoc.y2023

import aoc.common.Utils.println
import aoc.common.Utils.readInput

fun main() {
    val wordConnector: Map<String, String> =
        mapOf(
            Pair("one", "o1e"),
            Pair("two", "t2o"),
            Pair("three", "t3e"),
            Pair("four", "f4r"),
            Pair("five", "f5e"),
            Pair("six", "s6x"),
            Pair("seven", "s7n"),
            Pair("eight", "e8t"),
            Pair("nine", "n9e")
        )

    fun part1(input: List<String>): Int {
        var count = 0
        input.forEach { string ->
            val s = string.filter { it.isDigit() }
            val num = "${s.first()}${s.last()}".toInt()
            count += num
        }
        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        input.forEach { string ->
            var intString = string
            wordConnector.forEach { (t, u) -> intString = intString.replace(t, u) }
            val s = intString.filter { it.isDigit() }
            val num = "${s.first()}${s.last()}".toInt()
            count += num
        }
        return count
    }

    val testInputP1 = readInput(2023, "test/Day01P1")
    check(part1(testInputP1) == 142)

    val testInputP2 = readInput(2023, "test/Day01P2")
    check(part2(testInputP2) == 281)

    val input = readInput(2023, "Day01")
    part1(input).println()
    part2(input).println()
}
