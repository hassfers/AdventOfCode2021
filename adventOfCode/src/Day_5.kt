package com.mypackage

import kotlin.math.*

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
		get() = false

	inner class GridPoint(val x: Int, val y: Int) {
		override fun toString(): String {
			return "(x: $x, y: $y)"
		}

		fun equals(other: GridPoint?): Boolean {
			return x == other?.x
					&& y == other?.y
		}
	}

	inner class CorespondigGridPoint(val startPoint: GridPoint, val endPoint: GridPoint) {
		fun generatePointBetween(): Array<GridPoint> {
			val diffX = endPoint.x - startPoint.x
			val diffY = endPoint.y - startPoint.y
			val count = max(abs(diffX), abs(diffY)) + 1
			val distance =
				sqrt(
					((startPoint.x - endPoint.x) * (startPoint.x - endPoint.x)
							+ (startPoint.y - endPoint.y) * (startPoint.y - endPoint.y)).toDouble()
				) / count
			val fi: Double = atan2((diffY).toDouble(), (diffX).toDouble())

			return arrayOf(startPoint).plus(generateNextPoints(startPoint, endPoint, emptyList()).toTypedArray())
		}

		fun generateNextPoints(startPoint: GridPoint, endPoint: GridPoint, points: List<GridPoint>): List<GridPoint> {
			if (startPoint.equals(endPoint)) {
				return points
			}

			val diffX = endPoint.x - startPoint.x
			val diffY = endPoint.y - startPoint.y

			val distance = if (diffX == 0 || diffY == 0) {
				1.0
			} else {
				sqrt(2.0)
			}
			val fi: Double = atan2((diffY).toDouble(), (diffX).toDouble())
			val nextPoint = GridPoint(
				(startPoint.x + distance * cos(fi)).roundToInt(),
				(startPoint.y + distance * sin(fi)).roundToInt()
			)

			return generateNextPoints(nextPoint, endPoint, points.plus(nextPoint))
		}
	}

	override fun solvePartOne(input: Array<String>): String {
		return numberOfCrossings(process(input).filter {
			it.startPoint.x == it.endPoint.x
					|| it.startPoint.y == it.endPoint.y
		}.toTypedArray()).toString()
	}

	override fun solvePartTwo(input: Array<String>): String {
		return numberOfCrossings(process(input).toTypedArray()).toString()
	}

	fun process(array: Array<String>): List<CorespondigGridPoint> {
		return array
			.toList()
			.map {
				it.split(" -> ")
					.map { it.split(",") }
					.map {
						GridPoint(it.first().toInt(), it[1].toInt())
					}
			}
			.map {
				CorespondigGridPoint(it.first(), it[1])
			}
	}

	fun numberOfCrossings(array: Array<CorespondigGridPoint>): Int {
		var grid = emptyMap<String, Int>().toMutableMap()
		array.forEach { corespondigGridPoint ->
			corespondigGridPoint.generatePointBetween().forEach { gridPoint ->
				if (grid.containsKey(gridPoint.toString())) {
					grid[gridPoint.toString()] = grid[gridPoint.toString()]?.plus(1)!!
				} else {
					grid.put(gridPoint.toString(), 1)
				}
			}
		}
		return grid.count {
			it.value > 1
		}
	}
}