package aoc.y2023

import aoc.common.Utils.println
import aoc.common.Utils.readInput

val validBag = Bag(14, 12, 13)
fun main() {

    fun String.getSubset(): Bag {
        val balls = this.trim().replace(" ", "").replace(" ", "").split(",")
        var red = 0
        var blue = 0
        var green = 0
        balls.forEach {
            with(it) {
                when {
                    contains("red") -> red = removeSuffix("red").toInt()
                    contains("blue") -> blue = removeSuffix("blue").toInt()
                    contains("green") -> green = removeSuffix("green").toInt()
                    else -> {}
                }
            }
        }
        return Bag(blue, red, green)
    }

    fun String.getSubsets(): List<Bag> {
        val subsets: ArrayList<Bag> = ArrayList()
        val rawSubsets = this.trim().replace(" ", "").split(";")
        rawSubsets.forEach { subsets.add(it.getSubset()) }
        return subsets
    }

    fun String.toGame(): Game {
        val row = this.split(":")
        val rawGameId = row[0]
        val rawSubset = row[1]

        val id = rawGameId.removePrefix("Game ").toInt()
        val subsets = rawSubset.getSubsets()
        return Game(id, subsets)
    }

    fun List<String>.getGames(): List<Game> {
        val games: ArrayList<Game> = ArrayList()
        this.forEach { games.add(it.toGame()) }
        return games
    }

    fun calculateMaxBag(subsets: List<Bag>): Bag {
        var maxRed = 0
        var maxBlue = 0
        var maxGreen = 0

        subsets.forEach { bag ->
            if (bag.red > maxRed) maxRed = bag.red
            if (bag.blue > maxBlue) maxBlue = bag.blue
            if (bag.green > maxGreen) maxGreen = bag.green
        }
        return Bag(blue = maxBlue, red = maxRed, green = maxGreen)
    }

    fun powerSet(subsets: List<Bag>): Int {
        val maxBag = calculateMaxBag(subsets)
        return maxBag.blue * maxBag.red * maxBag.green
    }

    fun part1(input: List<String>): Int {
        val games = input.getGames()

        return games.sumOf { if (it.isValid()) it.id else 0 }
    }

    fun part2(input: List<String>): Int {
        val games = input.getGames()
        return games.sumOf { powerSet(it.subsets) }
    }

    val testInputP1 = readInput(2023, "test/Day02P1")
    check(part1(testInputP1) == 8)

    val testInputP2 = readInput(2023, "test/Day02P2")
    check(part2(testInputP2) == 2286)

    val input = readInput(2023, "Day02")
    part1(input).println()
    part2(input).println()
}

data class Game(
    val id: Int,
    val subsets: List<Bag>
) {
    fun isValid(): Boolean {
        this.subsets.forEach { subset ->
            if (subset.blue > validBag.blue ||
                subset.red > validBag.red ||
                subset.green > validBag.green
            ) {
                return false
            }
        }
        return true
    }
}

data class Bag(
    val blue: Int,
    val red: Int,
    val green: Int
)
