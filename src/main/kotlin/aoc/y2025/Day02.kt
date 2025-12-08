package aoc.y2025

import aoc.common.Utils.readInput

fun main() {
    val p1Result: Long = 1227775554
    val p2Result = 4174379265

    fun doesRepeatTwice(lng: Long): Boolean =
        lng.toString().substring(0, (lng.toString().length / 2)) == lng.toString().substring(lng.toString().length / 2)

    fun getFactors(num: Int): Set<Int> = (1..num/2).filter { num % it == 0 }.toSet()

    fun doesRepeatN (lng: Long): Boolean {

        val factors = getFactors(lng.toString().length)
        factors.forEach { factor ->
            if(lng.toString().chunked(factor).toSet().size == 1)
                return true
        }
        return false
    }

    fun part1(input: List<String>): Long =
        input[0].split(',').map { string ->
            val (start, end) = string.split('-')
            (start.toLong() .. end.toLong()).toList()
                .filter { it.toString().length.mod(2) == 0 }
                .filter { doesRepeatTwice(it) }
        }.flatten().sum()

    fun part2(input: List<String>): Long =
        input[0].split(',').map { string ->
            val (start, end) = string.split('-')
            (start.toLong() .. end.toLong()).toList()
                .filter { doesRepeatN(it) }
    }.flatten().sum()

    val testInput: List<String> = readInput(2025, "test/Day02P1")
    val input: List<String> = readInput(2025, "Day02")

    check(part1(testInput) == p1Result)
    println(part1(input))
    check(part2(testInput) == p2Result)
    println(part2(input))
}
