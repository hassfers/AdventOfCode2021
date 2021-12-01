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