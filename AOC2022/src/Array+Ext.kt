package com.mypackage.AOC2022


fun <E> List<E>.floatingGroupBy(number: Int): List<List<E>> {
	var self = this.toMutableList()
	var groupedList = emptyList<List<E>>().toMutableList()

	for (i in 0..self.size) {
		groupedList.add(self.take(3))
		self.removeFirstOrNull()
	}
	return groupedList
}

fun <E> List<List<E>>.genericTranspose(): List<List<E>> {
	val input = this
	var buffer = mutableListOf<E>()

	this.first().forEachIndexed { column, _ ->
		this.forEachIndexed { row, _ -> buffer += input[row][column] }
	}

	return buffer.chunked(this.size)
}

fun List<String>.transpose(): List<String> {
	val input = this.map { it.toCharArray() }
	var buffer = ""

	this.first().forEachIndexed { column, _ ->
		this.forEachIndexed { row, _ -> buffer += input[row][column] }
	}

	return buffer.chunked(this.size)
}

fun List<String>.column(number: Int): String {
	return this.map { it[number] }.toCharArray().contentToString()
}

fun List<String>.splitBy(string: String): List<List<String>> {
	var buffer = this
	var returnValue = mutableListOf<List<String>>()
	while (buffer.isNotEmpty()) {
		val firstIndex = buffer.indexOfFirst { it == string }
		buffer = if (firstIndex < 0) {
			returnValue.plusAssign(buffer)
			emptyList()
		} else {
			returnValue.plusAssign(buffer.subList(0, firstIndex))
			buffer.drop(firstIndex + 1)
		}
	}
	return returnValue
}

fun String.toStringArray(): Array<String> {
	return this.map { "$it" }.toTypedArray()
}

fun List<String>.toConnectedString(): String {
	return this.joinToString(separator = "", prefix = "", postfix = "")
}

fun String.containsAllChars(stringToChecK: String): Boolean {
	return stringToChecK.toCharArray().all {
		this.toCharArray().contains(it)
	}
}

val String.asciiNumber get() = this.toCharArray()[0].toInt()