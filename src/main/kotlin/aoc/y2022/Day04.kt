package aoc.y2022

import aoc.common.Utils.println
import aoc.common.Utils.readInput
import java.util.Collections

fun main() {
    fun getPair(s: String): ElfPair {
        val rawPair = s.split(",")
        val rawElf1 = rawPair[0].split("-")
        val rawElf2 = rawPair[1].split("-")
        return ElfPair(
            (rawElf1[0].toInt()..rawElf1[1].toInt()).toList(),
            (rawElf2[0].toInt()..rawElf2[1].toInt()).toList()
        )
    }

    fun fullyContainedPair(pair: ElfPair): Int {
        if (Collections.indexOfSubList(pair.elf1, pair.elf2) != -1 ||
            Collections.indexOfSubList(pair.elf2, pair.elf1) != -1
        ) {
            return 1
        }
        return 0
    }

    fun partiallyContainedPair(pair: ElfPair): Int {
        pair.elf1.forEach { if (pair.elf2.contains(it)) return 1 }
        return 0
    }

    fun part1(input: List<String>): Int {
        val pairs: ArrayList<ElfPair> = ArrayList()
        input.forEach { pairs.add(getPair(it)) }
        return pairs.sumOf { fullyContainedPair(it) }
    }

    fun part2(input: List<String>): Int {
        val pairs: ArrayList<ElfPair> = ArrayList()
        input.forEach { pairs.add(getPair(it)) }
        return pairs.sumOf { partiallyContainedPair(it) }
    }

    val testInputP1 = readInput(2022, "test/Day04P1")
    check(part1(testInputP1) == 2)

    val testInputP2 = readInput(2022, "test/Day04P2")
    check(part2(testInputP2) == 4)

    val input = readInput(2022, "Day04")
    part1(input).println()
    part2(input).println()
}

data class ElfPair(
    val elf1: List<Int>,
    val elf2: List<Int>
)
