package com.mypackage.AOC2022

import java.lang.RuntimeException

class Day_2 : Day {
	override val day: DayIdentifier
		get() = DayIdentifier.Day_2
	override val example: List<String>
		get() =
			listOf(
				"A Y",
				"B X",
				"C Z"
			)

//			listOf(
//				"A X",
//				"A Y",
//				"A Z",
//				"B X",
//				"B Y",
//				"B Z",
//				"C X",
//				"C Y",
//				"C Z",
//			)


	override val isRunningExample: Boolean
		get() = false

	//A for Rock, B for Paper, and C for Scissors
	//X for Rock, Y for Paper, and Z for Scissors.
	fun outcomeRound(me: String, you: String): Int {
		val meNumber = (me.asciiNumber - 87)
		val youNumber = you.asciiNumber - 64
		if (me == "X" && you == "C") {
			return 6
		}
		if (me == "Z" && you == "A") {
			return 0
		}
		val compareResult = ((meNumber)).compareTo(youNumber)
		return compareResult * 3 + 3
	}


	fun choiseForEnding(you: String, result: String): String {
		return when (result) {
			"X" ->
				return when (you) {
					"A" -> {
						"Z"
					}
					"B" -> {
						"X"
					}
					"C" -> {
						"Y"
					}
					else -> throw RuntimeException("geht net")

				}
			"Y" -> return Char(you.asciiNumber - 64 + 87).toString()
			"Z" -> return when (you) {
				"A" -> {
					"Y"
				}
				"B" -> {
					"Z"
				}
				"C" -> {
					"X"
				}
				else -> throw RuntimeException("geht net")
			}
			else -> throw RuntimeException("geht net")
		}
	}


	override fun solvePartOne(input: List<String>): String {
		val operations = input.map { it.split(" ") }

		val games = operations.map { Pair(it[1], outcomeRound(it[1], it[0])) }
		val test = games.map { it.first.asciiNumber - 87 + it.second }
		return "${test.sum()}"
	}

	override fun solvePartTwo(input: List<String>): String {
		val operations = input.map { it.split(" ") }
		val games2 = operations.map { Pair(it[1], choiseForEnding(it[0], it[1])) }
		val games =
			operations.map { Pair(choiseForEnding(it[0], it[1]), outcomeRound(choiseForEnding(it[0], it[1]), it[0])) }
		val test = games.map { it.first.asciiNumber - 87 + it.second }
		return "${test.sum()}"
	}
}