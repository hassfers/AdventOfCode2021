package com.mypackage

import kotlin.jvm.internal.Intrinsics

class DayOne(override val isRunningExample: Boolean = false) : Day {
	override fun solvePartOne(input: Array<String>): String {

		val intInput = input.map { it.toInt() }
		return intInput
			.mapIndexed { index, i ->
			if (index != 0 && intInput[index-1] < i) {
				"increased"
			} else {
				"nothing"
			}
		}
			.count {
				it == "increased"
			}
		.toString()
	}

	override fun solvePartTwo(input: Array<String>): String {
		return solvePartOne(input.toList()
			.map { it.toInt() }
			.floatingGroupBy(3)
			.filter { it.size == 3 }
			.map { it.sum() }
			.map { it.toString() }
			.toTypedArray())
	}

	override val day: DayIdentifier
		get() = DayIdentifier.Day_1
	override val example: Array<String>
		get() = TODO("Not yet implemented")
}

