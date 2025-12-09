package aoc.common

import java.math.BigInteger
import java.security.MessageDigest

object Utils {
    fun readInput(year: Int, name: String): List<String> {
        val resource = Utils::class.java.getResourceAsStream("/inputs/$year/$name.txt")
            ?: throw IllegalArgumentException("Input file not found: /inputs/$year/$name.txt")
        return resource.bufferedReader().readLines()
    }

    fun readGrid(year: Int, name: String): Grid {
        val lines = readInput(year, name)
        return Grid(lines.map { it.toList() })
    }

    fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
        .toString(16)
        .padStart(32, '0')

    fun Any?.println() = println(this)
}
