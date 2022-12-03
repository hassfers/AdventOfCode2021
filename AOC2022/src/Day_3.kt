package com.mypackage.AOC2022

class Day_3 : Day {
	override val day: DayIdentifier
		get() = DayIdentifier.Day_3
	override val example: List<String>
		get() = listOf(
			"vJrwpWtwJgWrhcsFMMfFFhFp",
			"jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
			"PmmdzqPrVvPwwTWBwg",
			"wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
			"ttgJtRGJQctTZtZT",
			"CrZsJsPPZsGzwwsLwLmpwMDw"
		)
	override val isRunningExample: Boolean
		get() = false

	override fun solvePartOne(input: List<String>): String {
		val rucksack = input
			.map { it.chunked(it.length / 2) }
			.map { it.first().toList().intersect(it[1].toList()).joinToString() }
			.map { if (it.asciiNumber > 91) it.asciiNumber - 96 else it.asciiNumber - 38 }
		return rucksack.sum().toString()
	}

	override fun solvePartTwo(input: List<String>): String {
		return super.solvePartTwo(input)
	}
}