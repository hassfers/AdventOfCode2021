package com.mypackage

class Day_3(override val isRunningExample: Boolean) : Day {
	override val day: DayIdentifier
		get() = DayIdentifier.Day_3
	override val example: Array<String>
		get() = arrayOf(
			"00100",
			"11110",
			"10110",
			"10111",
			"10101",
			"01111",
			"00111",
			"11100",
			"10000",
			"11001",
			"00010",
			"01010"
		)

	override fun solvePartOne(input: Array<String>): String {
		val transpose = input
			.toList()
			.transpose()
			.map {
				it.groupBy { it }
					.mapValues {
						it.value.size
					}
			}

		val gammaRate = Integer.parseInt(
			transpose.map {
				it.maxByOrNull { it.value }
			}.map {
				it!!.key
			}.toCharArray().concatToString(),
			2
		)

		val epsilonRate = Integer.parseInt(
			transpose.map {
				it.minByOrNull { it.value }
			}.map {
				it!!.key
			}.toCharArray().concatToString(),
			2
		)
		return "$gammaRate, $epsilonRate, ${epsilonRate * gammaRate}"
	}

	override fun solvePartTwo(input: Array<String>): String {
		val oxygen = getOxygen(input).first()
		val co2rating = getCo2(input, 0).first()

		return ("${oxygen} ${co2rating} ${Integer.parseInt(co2rating, 2) * Integer.parseInt(oxygen, 2)}")
	}

	fun processWithComperator(array: Array<String>, position: Int, comparator: (Int, Int) -> Char): Array<String> {
		if (array.size == 1) {
			return array
		}

		val columnEntry = array.toList()
			.column(position)
			.groupBy { it }
			.mapValues {
				it.value.size
			}

		val key = comparator(columnEntry['0']?:0, columnEntry['1']?:0)
		return processWithComperator(
			array.filter {
				it[position] == key
			}.toTypedArray(),
			position + 1,
			comparator
		)
	}

	fun getOxygen(array: Array<String>): Array<String> {
		return processWithComperator(array, 0, comparator = { zeros, ones ->
			if ((zeros.compareTo(ones)) == 1) {
				'0'
			} else {
				'1'
			}
		})
	}

	fun getCo2(array: Array<String>, position: Int): Array<String> {
		return processWithComperator(array, 0, comparator = { zeros, ones ->
			if ((ones.compareTo(zeros)) == -1) {
				'1'
			} else {
				'0'
			}
		})
	}
}
