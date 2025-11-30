package aoc.y2022

import aoc.common.Utils.println
import aoc.common.Utils.readInput

fun main() {

    fun calculateScore(weapon: Weapon, outcome: Outcome): Int {
        val weaponScore = when (weapon) {
            Weapon.ROCK -> 1
            Weapon.PAPER -> 2
            Weapon.SCISSORS -> 3
            Weapon.UNKNOWN -> throw Exception("Cannot compute score of weapon UNKNOWN")
        }

        val outcomeScore = when (outcome) {
            Outcome.LOSS -> 0
            Outcome.DRAW -> 3
            Outcome.WIN -> 6
            Outcome.ERROR -> throw Exception("Cannot compute score of outcome ERROR")
        }

        return weaponScore + outcomeScore
    }

    fun calculateOutcome(game1: Game1): Outcome =
        when (game1.second) {
            Weapon.ROCK -> when (game1.first) {
                Weapon.ROCK -> Outcome.DRAW
                Weapon.PAPER -> Outcome.LOSS
                Weapon.SCISSORS -> Outcome.WIN
                Weapon.UNKNOWN -> Outcome.ERROR
            }

            Weapon.PAPER -> when (game1.first) {
                Weapon.ROCK -> Outcome.WIN
                Weapon.PAPER -> Outcome.DRAW
                Weapon.SCISSORS -> Outcome.LOSS
                Weapon.UNKNOWN -> Outcome.ERROR
            }

            Weapon.SCISSORS -> when (game1.first) {
                Weapon.ROCK -> Outcome.LOSS
                Weapon.PAPER -> Outcome.WIN
                Weapon.SCISSORS -> Outcome.DRAW
                Weapon.UNKNOWN -> Outcome.ERROR
            }

            Weapon.UNKNOWN -> Outcome.ERROR
        }

    fun getWeapon(s: String): Weapon =
        when (s) {
            "A", "X" -> Weapon.ROCK
            "B", "Y" -> Weapon.PAPER
            "C", "Z" -> Weapon.SCISSORS
            else -> {
                Weapon.UNKNOWN
            }
        }

    fun String.toGame1(): Game1 {
        val s = this.split(" ")
        return Game1(first = getWeapon(s[0]), second = getWeapon(s[1]))
    }

    fun List<String>.getGames1(): List<Game1> {
        val games: ArrayList<Game1> = ArrayList()
        this.forEach { games.add(it.toGame1()) }
        return games
    }

    fun part1(input: List<String>): Int {
        val games = input.getGames1()
        var score: Int = 0
        games.forEach { score += calculateScore(it.second, calculateOutcome(it)) }
        return score
    }

    fun calculateWeapon(game2: Game2) =
        when (game2.second) {
            Outcome.WIN -> when (game2.first) {
                Weapon.ROCK -> Weapon.PAPER
                Weapon.PAPER -> Weapon.SCISSORS
                Weapon.SCISSORS -> Weapon.ROCK
                Weapon.UNKNOWN -> Weapon.UNKNOWN
            }

            Outcome.LOSS -> when (game2.first) {
                Weapon.ROCK -> Weapon.SCISSORS
                Weapon.PAPER -> Weapon.ROCK
                Weapon.SCISSORS -> Weapon.PAPER
                Weapon.UNKNOWN -> Weapon.UNKNOWN
            }

            Outcome.DRAW -> when (game2.first) {
                Weapon.ROCK -> Weapon.ROCK
                Weapon.PAPER -> Weapon.PAPER
                Weapon.SCISSORS -> Weapon.SCISSORS
                Weapon.UNKNOWN -> Weapon.UNKNOWN
            }

            Outcome.ERROR -> Weapon.UNKNOWN
        }

    fun getOutcome(s: String): Outcome =
        when (s) {
            "X" -> Outcome.LOSS
            "Y" -> Outcome.DRAW
            "Z" -> Outcome.WIN
            else -> {
                Outcome.ERROR
            }
        }

    fun String.toGame2(): Game2 {
        val s = this.split(" ")
        return Game2(first = getWeapon(s[0]), second = getOutcome(s[1]))
    }

    fun List<String>.getGames2(): List<Game2> {
        val games: ArrayList<Game2> = ArrayList()
        this.forEach { games.add(it.toGame2()) }
        return games
    }

    fun part2(input: List<String>): Int {
        val games = input.getGames2()
        var score: Int = 0
        games.forEach { score += calculateScore(calculateWeapon(it), it.second) }
        return score
    }

    val input = readInput(2022, "Day02")
    part1(input).println()
    part2(input).println()
}

enum class Outcome {
    WIN, DRAW, LOSS, ERROR
}

enum class Weapon {
    ROCK, PAPER, SCISSORS, UNKNOWN
}

typealias Game1 = Pair<Weapon, Weapon>

typealias Game2 = Pair<Weapon, Outcome>
