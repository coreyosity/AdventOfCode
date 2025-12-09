package aoc.y2025

import aoc.common.Utils.readInput

fun main() {

    fun List<LongRange>.distinctRanges(): List<LongRange> {
        val sorted = this.sortedBy { it.first }
        if (sorted.isEmpty()) return emptyList()
        
        val result = mutableListOf(sorted.first())
        for (range in sorted.drop(1)) {
            val last = result.last()
            if (range.first <= last.last + 1) {
                result[result.lastIndex] = last.first..maxOf(last.last, range.last)
            } else {
                result.add(range)
            }
        }
        return result
    }

    fun LongRange.size(): Long =
        if (isEmpty()) 0 else last - first + 1

    fun part1(input: List<String>): Int {
        val (numbersStr, rangesWithBlank) = input.partition { it.toLongOrNull() != null }
        val ranges = rangesWithBlank.dropLast(1).map { string ->
            val (start, end) = string.split('-')
            (start.toLong()..end.toLong())
        }
        return numbersStr.map { it.toLong() }.fold(0) { acc, number ->
            acc + if (ranges.any { number in it }) 1 else 0
        }
    }

    fun part2(input: List<String>): Long {
        val (_, rangesWithBlank) = input.partition { it.toLongOrNull() != null }
        val distinctRanges = rangesWithBlank.dropLast(1).map { string ->
            val (start, end) = string.split('-')
            (start.toLong()..end.toLong())
        }.distinctRanges()

        return distinctRanges.sumOf { range -> range.size() }
    }

    val testInput = readInput(2025, "test/Day05P1")
    val input = readInput(2025, "Day05")
    check(part1(testInput) == 3)
    println(part1(input))
    println(part2(testInput))
    check(part2(testInput) == 14.toLong())
    println(part2(input))

}