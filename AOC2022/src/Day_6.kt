package com.mypackage.AOC2022

class Day_6 : Day() {
    override val example: List<String>
        get() = listOf(
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb",
            "bvwbjplbgvbhsrlpgdmjqwftvncz",
            "nppdvjthqldpwncqszvftbrmjlhg",
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"
        )

    override val isRunningExample: Boolean
        get() = false

    fun readMarker(input: String, number: Int): Int {
        val marker = input
            .windowed(number)
            .first { it.toSet().size == number }
        return input.indexOf(marker) + number
    }

    override fun solvePartOne(input: List<String>): String {
        return if (isRunningExample) {
            input.map { readMarker(it, 4) }.joinToString(separator = ",")
        } else {
            return input.map {
                readMarker(it, 4)
            }.toString()
        }
    }

    override fun solvePartTwo(input: List<String>): String {
        return if (isRunningExample) {
            input.map { readMarker(it, 14) }.joinToString(separator = ",")
        } else {
            return input.map { readMarker(it, 14) }.toString()
        }
    }
}