package aoc.y2025

import aoc.common.Utils.readInput
import kotlin.math.abs

fun main() {
    val startPosition = 50
    val digits = 100

    fun getOperations(input: List<String>): List<Pair<Char, Int>> =
        input.map { Pair(it[0], it.substring(1).toInt()) }

    fun landsOnZero(position: Int) = if (position == 0) 1 else 0

    fun getCompleteTurnsPlusRemainder(value: Int): Pair<Int,Int> =
        Pair(abs(value.floorDiv(digits)), value.mod(digits))

    fun calculateNewPosition(startPos: Int, positionAfterRotation: Int): Pair<Int, Int> {
        val crossed = when {
            startPos == 0 && positionAfterRotation < 0 -> 0  // Starting at 0 going left doesn't count
            positionAfterRotation !in 0..100 -> 1
            else -> 0
        }
        return positionAfterRotation.mod(digits) to crossed
    }

    fun part1(operations: List<Pair<Char, Int>>): Int {

        val (_, count) = operations.fold(startPosition to 0) { (lockPosition, count), (direction, value) ->
            val newLockPosition = when (direction) {
                'L' -> (lockPosition +(digits - value)).mod(digits)
                'R' -> (lockPosition + value).mod(digits)
                else -> lockPosition
            }
            newLockPosition to landsOnZero(newLockPosition) + count
        }
        return count
    }

    fun part2(operations: List<Pair<Char, Int>>): Int {
        val (_, count) = operations.fold(startPosition to 0) { (lockPosition, count), (direction, value) ->
            val (fullRotations, simplifiedValue) = getCompleteTurnsPlusRemainder(value)
            val (newLockPosition, crossZero) = when (direction) {
                'L' -> calculateNewPosition(lockPosition, lockPosition - simplifiedValue)
                'R' -> calculateNewPosition(lockPosition, lockPosition + simplifiedValue)
                else -> 0 to 0
            }
            newLockPosition to fullRotations + crossZero + landsOnZero(newLockPosition) + count
        }
        return count
    }

    val testInput: List<String> = readInput(2025, "test/Day01P1")
    val input: List<String> = readInput(2025, "Day01")

    check(part1(getOperations(testInput)) == 3)
    println("Part 1: ${part1(getOperations(input))}")
    check(part2(getOperations(testInput)) == 6)
    println("Part 2: ${part2(getOperations(input))}")
}
