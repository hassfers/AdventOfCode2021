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

	override fun solvePartTwo(input: Array<String>): String {
		val start = input.first().split(",").map { it.toInt() }.toIntArray()
		return nextIteration(start, target = 256).map { it.value }.sum().toString()
	}

	fun nextIteration(input: IntArray, target: Int): Map<Int, Long> {
		var map = input.map { number ->
			number to input.count {
				it == number
			}.toLong()
		}.toSet()
			.toMap()
		repeat(target) {
			var newMap = mutableMapOf<Int, Long>(
				0 to 0,
				1 to 0,
				2 to 0,
				3 to 0,
				4 to 0,
				5 to 0,
				6 to 0,
				7 to 0,
				8 to 0
			)
			map.keys.forEach { i ->
				if (i == 0) {
					newMap[6] = newMap[6]!!.plus(map[i]!!)
					newMap[8] = map[i]!!
				} else {
					newMap[i - 1] = newMap[i - 1]!!.plus(map[i]!!)
				}
			}
			map = newMap
		}
		return map
	}


	fun nextIteration(input: IntArray, index: Int, target: Int): IntArray {
		if (index == target) {
			return input
		}
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
		return nextIteration(nextArray.plus(IntArray(count) { 8 }), index + 1, target)
	}
}