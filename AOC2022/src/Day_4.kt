package com.mypackage.AOC2022

class Day_4 : Day() {
	override val day: DayIdentifier
		get() = DayIdentifier.Day_4
	override val example: List<String>
		get() = listOf(
			"2-4,6-8",
			"2-3,4-5",
			"5-7,7-9",
			"2-8,3-7",
			"6-6,4-6",
			"2-6,4-8"
		)
	override val isRunningExample: Boolean
		get() = false

	override fun solvePartOne(input: List<String>): String {
		val pairs = input
			.map {
				it.split(',')
					.map {
						it.split("-")
							.map {
								it.toInt()
							}
							.zipWithNext().first()
					}
					.zipWithNext().first()
			}

		val fullContaining = pairs.count {
			it.first.intermediateNumbers.minus(it.second.intermediateNumbers).isEmpty() ||
					it.second.intermediateNumbers.minus(it.first.intermediateNumbers).isEmpty()
		}

		return fullContaining.toString()
	}


	override fun solvePartTwo(input: List<String>): String {
		val pairs = input
			.map {
				it.split(',')
					.map {
						it.split("-")
							.map {
								it.toInt()
							}
							.zipWithNext().first()
					}
					.zipWithNext().first()
			}

		val fullContaining = pairs.count {
			it.first.intermediateNumbers.intersect(it.second.intermediateNumbers).isNotEmpty() ||
					it.second.intermediateNumbers.intersect(it.first.intermediateNumbers).isNotEmpty()
		}
		return fullContaining.toString()
	}
}


val Pair<Int, Int>.intermediateNumbers: Set<Int>
	get() {
		return (this.first..this.second).toSet()
	}