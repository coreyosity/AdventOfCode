package aoc.y2022

import aoc.common.Utils.println
import aoc.common.Utils.readInput

fun main() {
    fun elves(input: List<String>): List<Elf> {
        val elves: ArrayList<Elf> = ArrayList()
        var calories = 0
        input.forEach {
            if (it != "") {
                calories += it.toInt()
            } else {
                elves.add(Elf(calories))
                calories = 0
            }
        }
        return elves
    }

    fun part1(input: List<String>): Int {
        val elves: List<Elf> = elves(input)
        return elves.maxOf { it.calories }
    }

    fun part2(input: List<String>): Int {
        val topElves = elves(input).sortedBy { it.calories }.reversed()
        return topElves[0].calories + topElves[1].calories + topElves[2].calories
    }

    val input = readInput(2022, "Day01")
    part1(input).println()
    part2(input).println()
}

data class Elf(
    val calories: Int
)
