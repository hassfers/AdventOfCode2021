package com.mypackage

class Day_6 : Day {
	override val day: DayIdentifier
		get() = DayIdentifier.Day_6
	override val example: Array<String>
		get() =
			arrayOf(
				"3,4,3,1,2"
			)
	override val isRunningExample: Boolean
		get() = false

	override fun solvePartOne(input: Array<String>): String {
		val start = input.first().split(",").map { it.toInt() }.toIntArray()
		val fishs = nextIteration(start, 0, 80)
		return fishs.size.toString()
	}

	fun nextIteration(input: IntArray, index: Int, target: Int): IntArray {
		if (index == target) {
			return input
		}
//		println(index)
//		println(input.joinToString())
		val count = input.count {
			it == 0
		}
		var nextArray = input.map {
			if (it > 0) {
				it - 1
			} else {
				6
			}
		}.toIntArray()

//		println(nextArray.plus(IntArray(count) { 8 }).joinToString())
//		println()
		return nextIteration(nextArray.plus(IntArray(count) { 8 }), index + 1, target)
	}
}