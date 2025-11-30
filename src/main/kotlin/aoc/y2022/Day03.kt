package aoc.y2022

import aoc.common.Utils.println
import aoc.common.Utils.readInput

fun main() {

    fun String.splitAtIndex(index: Int) = take(index) to substring(index)

    fun getRucksack(items: String): Rucksack {
        val compartments = items.splitAtIndex(items.length / 2)
        return Rucksack(items, compartments.first, compartments.second)
    }

    fun buildGroups(rucksacks: List<Rucksack>): List<Group> {
        val groups: ArrayList<Group> = ArrayList()
        for (i in rucksacks.indices step 3) {
            groups.add(Group(rucksacks[i], rucksacks[i + 1], rucksacks[i + 2]))
        }
        return groups
    }

    fun calculateValue(c: Char): Int {
        return "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(c) + 1
    }

    fun getPriority1(rucksack: Rucksack): Int {
        for (c in rucksack.compartment1) {
            if (rucksack.compartment2.contains(c)) {
                return calculateValue(c)
            }
        }
        return 0
    }

    fun getPriority2(group: Group): Int {
        for (c in group.rucksack1.totalItems) {
            if (group.rucksack2.totalItems.contains(c) &&
                group.rucksack3.totalItems.contains(c)
            ) {
                return calculateValue(c)
            }
        }
        return 0
    }

    fun part1(input: List<String>): Int {
        val rucksacks: ArrayList<Rucksack> = ArrayList()
        input.forEach { rucksacks.add(getRucksack(it)) }
        return rucksacks.sumOf { getPriority1(it) }
    }

    fun part2(input: List<String>): Int {
        val rucksacks: ArrayList<Rucksack> = ArrayList()
        input.forEach { rucksacks.add(getRucksack(it)) }
        val groups = buildGroups(rucksacks)
        return groups.sumOf { getPriority2(it) }
    }

    val testInputP1 = readInput(2022, "test/Day03P1")
    check(part1(testInputP1) == 157)

    val testInputP2 = readInput(2022, "test/Day03P2")
    check(part2(testInputP2) == 70)

    val input = readInput(2022, "Day03")
    part1(input).println()
    part2(input).println()
}

data class Rucksack(
    val totalItems: String,
    val compartment1: String,
    val compartment2: String
)

data class Group(
    val rucksack1: Rucksack,
    val rucksack2: Rucksack,
    val rucksack3: Rucksack
)
