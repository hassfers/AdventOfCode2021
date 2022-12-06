package com.mypackage.AOC2022

class Day_1: Day() {
	override val day: DayIdentifier
		get() = DayIdentifier.Day_1
	override val example: List<String> = listOf(
			"1000",
			"2000",
			"3000",
			"",
			"4000",
			"",
			"5000",
			"6000",
			"",
			"7000",
			"8000",
			"9000",
			"",
			"10000"
		)

	override val isRunningExample: Boolean = false
	override fun solvePartOne(input: List<String>): String {
		val calories = input
			.splitBy("")
			.map {
			it.map {
				it.toInt()
			}
				.sum()
		}

		return calories.max().toString()
	}

	override fun solvePartTwo(input: List<String>): String {
		val calories = input
			.splitBy("")
			.map {
				it.map {
					it.toInt()
				}
					.sum()
			}.sortedDescending()

		return calories.take(3).sum().toString()
	}
}