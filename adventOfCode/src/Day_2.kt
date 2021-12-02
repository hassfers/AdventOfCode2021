package com.mypackage

class Day_2(override val isRunningExample: Boolean = false) :Day {
	override fun solvePartOne(input: Array<String>): String {
		var position = 0
		var depth = 0
		input.forEach { operation ->
			val container = operation.split(" ")
//			println(container)
			when (container.first()){
				"forward" -> {position += container[1].toInt()}
				"down" -> {depth += container[1].toInt()}
				"up" -> {depth -= container[1].toInt()}
			}

		}
		println("$position $depth")
		return "${position * depth}"
	}

	override fun solvePartTwo(input: Array<String>): String {
		var position = 0
		var depth = 0
		var aim = 0

		input.forEach { operation ->
			val container = operation.split(" ")
//			println(container)
			when (container.first()){
				"forward" -> {
					position += container[1].toInt()
					depth += (aim*container[1].toInt())
				}
				"down" -> {aim += container[1].toInt()}
				"up" -> {aim -= container[1].toInt()}
			}
		}
		return "${position * depth}"
	}

	override val day: DayIdentifier
		get() = DayIdentifier.Day_2

	override val example: Array<String>
		get() = arrayOf(
			"forward 5",
					"down 5",
					"forward 8",
					"up 3",
					"down 8",
					"forward 2"
		)
}