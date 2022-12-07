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

    override fun solvePartOne(input: List<String>): String {
        return if (isRunningExample) {
            input.map {
                val marker = it.windowed(4).first {
                    it.toSet().count() == 4
                }
                it.indexOf(marker) + 4
            }.joinToString(separator = ",")
        } else {
            return input.map {
                val marker = it.windowed(4).first {
                    it.toSet().count() == 4
                }
                it.indexOf(marker) + 4
            }.toString()
        }
    }

    override fun solvePartTwo(input: List<String>): String {
        if (isRunningExample) {
            input.map {
                val marker = it.windowed(4).first {
                    it.toSet().count() == 4
                }
                it.indexOf(marker) + 4
            }.joinToString(separator = ",")
        } else {
            return input.map {
                val marker = it.windowed(4).first {
                    it.toSet().count() == 4
                }
                it.indexOf(marker) + 4
            }.toString()
        }
    }
}