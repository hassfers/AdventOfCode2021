package com.mypackage

import kotlin.math.max

class Day_3(override val isRunningExample: Boolean) : Day {
	override val day: DayIdentifier
		get() = DayIdentifier.Day_3
	override val example: Array<String>
		get() = arrayOf(
			"00100",
			"11110",
			"10110",
			"10111",
			"10101",
			"01111",
			"00111",
			"11100",
			"10000",
			"11001",
			"00010",
			"01010"
		)

	override fun solvePartOne(input: Array<String>): String {
		val transpose = input
			.toList()
			.transpose()
			.map { it.groupBy { it } }

		val gammaRate = Integer.parseInt(
			transpose.map {
				it.maxByOrNull { it.value.size }
			}.map {
				it!!.key
			}.toCharArray().concatToString(), 2
		)

		val epsilonRate = Integer.parseInt(
			transpose.map {
				it.minByOrNull { it.value.size }
			}.map {
				it!!.key
			}.toCharArray().concatToString(), 2
		)


		return "$gammaRate, $epsilonRate, ${epsilonRate * gammaRate}"
	}
}