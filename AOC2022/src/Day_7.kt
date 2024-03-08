package com.mypackage.AOC2022

import java.lang.annotation.ElementType

class Day_7 : Day() {
	override val example: List<String>
		get() = listOf(
			"$ cd /",
			"$ ls",
			"dir a",
			"14848514 b.txt",
			"8504156 c.dat",
			"dir d",
			"$ cd a",
			"$ ls",
			"dir e",
			"29116 f",
			"2557 g",
			"62596 h.lst",
			"$ cd e",
			"$ ls",
			"584 i",
			"$ cd ..",
			"$ cd ..",
			"$ cd d",
			"$ ls",
			"4060174 j",
			"8033020 d.log",
			"5626152 d.ext",
			"7214296 k"
		)
	override val isRunningExample: Boolean
		get() = true

	enum class elementType {
		file, folder
	}


	class Element(val name: String, val children: List<Element>, val type: elementType, val size: Int = 0) {

	}


	override fun solvePartOne(input: List<String>): String {
		var fileTree = Element("/", emptyList(),elementType.folder)
		var currentElement = Element("nothing", emptyList(), elementType.folder)

		var counter = 0

		while (counter < input.size) {

		}


//		val commands = input.mapIndexed { index, s ->
//			if (s.startsWith("$ ")) index to s else null
//		}.filterNotNull().drop(1)
//
//
//
//
//
//		val operations = commands.mapIndexed { index, pair ->
//			when {
//				pair == commands.first() -> {
//				}
//
//				pair == commands.last() -> {
//					input.subList(pair.first, input.size).toList()
//				}
//
//				else -> {
//					input.subList(commands[index - 1].first, pair.first).toList()
//				}
//			}
//		}.drop(1)


		return super.solvePartOne(input)
	}

	override fun solvePartTwo(input: List<String>): String {
		return super.solvePartTwo(input)
	}

}