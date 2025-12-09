package aoc.y2025

import aoc.common.Grid
import aoc.common.Utils.readGrid

fun main() {
    val paperRoll='@'
    val visited='#'
    val empty='.'

    fun markAccessibleRolls(grid: Grid): Grid {
        val identifyAccessiblePaperRolls = grid.filter { point ->
            grid.get(point) == paperRoll && 
            grid.getAdjacent(point).count { grid.get(it) == paperRoll } < 4
        }.associateWith { visited }
        
        return grid.copy(modifications = identifyAccessiblePaperRolls)
    }

    fun part1(grid: Grid): Int {
        return markAccessibleRolls(grid).findAll(visited).size
    }

    fun part2(grid: Grid): Int {
        val markedGrid = markAccessibleRolls(grid)
        val rolls = markedGrid.findAll(visited).size
        if (rolls == 0) return 0
        
        val clearedGrid = markedGrid.copy(
            modifications = markedGrid.findAll(visited).associateWith { empty }
        )
        return rolls + part2(clearedGrid)
    }

    val testInput = readGrid(2025, "test/Day04P1")
    val input = readGrid(2025, "Day04")
    check(part1(testInput)==13)
    println(part1(input))
    check(part2(testInput)==43)
    println(part2(input))

}