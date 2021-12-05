package com.mypackage

class Day_5 {
import kotlin.math.abs
import kotlin.math.min

class Day_5 : Day {
	override val day: DayIdentifier
		get() = DayIdentifier.Day_5
	override val example: Array<String>
		get() = arrayOf(
			"0,9 -> 5,9",
			"8,0 -> 0,8",
			"9,4 -> 3,4",
			"2,2 -> 2,1",
			"7,0 -> 7,4",
			"6,4 -> 2,0",
			"0,9 -> 2,9",
			"3,4 -> 1,4",
			"0,0 -> 8,8",
			"5,5 -> 8,2"
		)
	override val isRunningExample: Boolean
		get() = true

	inner class GridPoint(val x: Int, val y: Int) {
		override fun toString(): String {
			return "(x: $x, y: $y)"
		}

	}

	inner class CorespondigGridPoint(val startPoint: GridPoint, val endPoint: GridPoint) {
		fun generateStraigthPointBetween(): Array<GridPoint> {
			return if (startPoint.x == endPoint.x) {
				IntArray(abs(startPoint.y - endPoint.y) + 1) { it + min(startPoint.y, endPoint.y) }.toList().map {
					GridPoint(startPoint.x, it)
				}.toTypedArray()
			} else {
				IntArray(abs(startPoint.x - endPoint.x) + 1) { it + min(startPoint.x, endPoint.x) }.toList().map {
					GridPoint(it, startPoint.y)
				}.toTypedArray()
			}
		}
	override fun solvePartOne(input: Array<String>): String {
		val points = input
			.toList()
			.map {
				it.split(" -> ")
					.map { it.split(",") }
					.map {
						GridPoint(it.first().toInt(), it[1].toInt())
					}
			}
			.filter {
				it[0].x == it[1].x || it[0].y == it[1].y
			}.map {
				CorespondigGridPoint(it.first(), it[1])
			}

		var grid = emptyMap<String, Int>().toMutableMap()

		points.forEach { corespondigGridPoint ->
			println("${corespondigGridPoint.startPoint} ${corespondigGridPoint.endPoint}")
			println(corespondigGridPoint.generatePointBetween().joinToString())
			println()
			corespondigGridPoint.generateStraigthPointBetween().forEach { gridPoint ->
				if (grid.containsKey(gridPoint.toString())) {
					grid[gridPoint.toString()] = grid[gridPoint.toString()]?.plus(1)!!
				} else {
					grid.put(gridPoint.toString(), 1)
				}
			}
		}
		val filter = grid.count {
			it.value > 1
		}
		return filter.toString()
	}
}