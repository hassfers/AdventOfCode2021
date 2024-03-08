package com.mypackage.AOC2023

import java.io.File
import kotlin.system.measureTimeMillis

abstract class Day {
	fun solve(){
		val time = measureTimeMillis{
			resultPartOne = solvePartOne(readInputFile())
			resultPartTwo = solvePartTwo(readInputFile())
		}

		println("-------------${day.toString()}-------------------")
		println("Part One: $resultPartOne")
		println("Part Two: $resultPartTwo")
		println("-------------${day.toString()}-------------------")
		println("took $time ms")
	}

	open fun solvePartOne(input: List<String>): String {
		return "not implemented yet"
	}

	open fun solvePartTwo(input:List<String>): String {
		return "not implemented yet"
	}

	var resultPartOne: String  = ""
	var resultPartTwo: String = ""

	val day = javaClass.simpleName

	abstract val example: List<String>
	abstract val isRunningExample: Boolean

	fun readInputFile(): List<String> {
//		File(".").walk().forEach {
//			println(it)
//		}
		if (isRunningExample) {
			return example
		}
		val filename = "./AOC2022/src/Input/" + javaClass.simpleName
		return File(filename).readLines()
	}
}
enum class DayIdentifier{
	Day_1, Day_2, Day_3, Day_4, Day_5, Day_6,
	Day_7, Day_8, Day_9, Day_10, Day_11, Day_12,
	Day_13, Day_14, Day_15, Day_16, Day_17, Day_18,
	Day_19, Day_20, Day_21, Day_22, Day_23, Day_24, Day_25
}