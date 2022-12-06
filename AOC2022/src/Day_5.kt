package com.mypackage.AOC2022

class Day_5 : Day() {
//	override val day: DayIdentifier
//		get() = DayIdentifier.Day_5
	override val example: List<String>
		get() = mutableListOf(
			"move 1 from 2 to 1",
			"move 3 from 1 to 3",
			"move 2 from 2 to 1",
			"move 1 from 1 to 2"
		)
	override val isRunningExample: Boolean
		get() = false


	val startingPoint: MutableList<MutableList<String>>
	get() =  if (isRunningExample) mutableListOf(
		mutableListOf(
			"Z", "N"
		),
		mutableListOf(
			"M", "C", "D"
		),
		mutableListOf(
			"P"
		)
	) else mutableListOf(
		mutableListOf("T", "Z", "B").asReversed(),
		mutableListOf("N", "D", "T", "H", "V").asReversed(),
		mutableListOf("D", "M", "F", "B").asReversed(),
		mutableListOf("L", "Q", "V", "W", "G", "J", "T").asReversed(),
		mutableListOf("M", "Q", "F", "V", "P", "G", "D", "W").asReversed(),
		mutableListOf("S", "F", "H", "G", "Q", "Z", "V").asReversed(),
		mutableListOf("W", "C", "T", "L", "R", "N", "S", "Z").asReversed(),
		mutableListOf("M", "R", "N", "J", "D", "W", "H", "Z").asReversed(),
		mutableListOf("S", "D", "F", "L", "Q", "M").asReversed(),
	)

	fun generateOperations(input: List<String>): List<List<Int>> {
		return input
			.map { it.split("from ") }
			.map {
				it.flatMap {
					it.split(" to ")
				}
			}
			.map {
				it.map {
					it.replace("move ", "")
				}
					.map {
						it.trim().toInt()
					}
			}
	}

//                                [M]     [W] [M]
//                            [L] [Q] [S] [C] [R]
//                            [Q] [F] [F] [T] [N] [S]
//                    [N]     [V] [V] [H] [L] [J] [D]
//                    [D] [D] [W] [P] [G] [R] [D] [F]
//                [T] [T] [M] [G] [G] [Q] [N] [W] [L]
//                [Z] [H] [F] [J] [D] [Z] [S] [H] [Q]
//                [B] [V] [B] [T] [W] [V] [Z] [Z] [M]





	override fun solvePartOne(input: List<String>): String {
		val startingPoint = startingPoint
		val operations = generateOperations(input)

		operations.forEach { operations ->
			val containersToMove = startingPoint[operations[1] - 1].takeLast(operations[0])
//			println(startingPoint)
//			println(operations.joinToString())
//			println(containersToMove)
//			println(new)
			startingPoint[operations[1] - 1] = startingPoint[operations[1] - 1].dropLast(operations[0]).toMutableList()
			startingPoint[operations[2] - 1].addAll(containersToMove.reversed())
//			println(startingPoint)
//			println("----")
		}
		return startingPoint.map { it.last() }.toConnectedString()
	}

	override fun solvePartTwo(input: List<String>): String {
		val startingPoint = startingPoint
		val operations = generateOperations(input)

		operations.forEach { operations ->
			val containersToMove = startingPoint[operations[1] - 1].takeLast(operations[0])
//			println(startingPoint)
//			println(operations.joinToString())
//			println(containersToMove)
//			println(new)
			startingPoint[operations[1] - 1] = startingPoint[operations[1] - 1].dropLast(operations[0]).toMutableList()
			startingPoint[operations[2] - 1].addAll(containersToMove)
//			println(startingPoint)
//			println("----")
		}

		return startingPoint.map { it.last() }.toConnectedString()
	}

}