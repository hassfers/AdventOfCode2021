package com.mypackage

fun <E> List<E>.floatingGroupBy(number: Int): List<List<E>> {
	var self = this.toMutableList()
	var groupedList = emptyList<List<E>>().toMutableList()

	for(i in 0..self.size){
		groupedList.add(self.take(3))
		self.removeFirstOrNull()
	}
	return groupedList
}

fun <E: Iterable<E>> List<E>.genericTranspose(): List<E> {
	val rows = this.size
	val column = this.first().count()
	return this
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
return 	String(this.map { it[number]}.toCharArray())
}