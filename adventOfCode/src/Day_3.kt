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
		var column = 0
		var bufferOxygen = input.toMutableList()
		while (bufferOxygen.size > 1) {
			val columnEntry = bufferOxygen
				.transpose()
				.map {
					it.groupBy { it }
						.mapValues {
							it.value.size
						}
				}[column]

			val key = if ((columnEntry['0']!! compareTo columnEntry['1']!!) == 1) {
				'0'
			} else {
				'1'
			}

			bufferOxygen =
				bufferOxygen.filter {
					it[column] == key
				}.toMutableList()

			column += 1
		}


		column = 0
		var co2rating = input.toMutableList()
		while (co2rating.size > 1) {
			val columnEntry = co2rating.transpose()
				.map {
					it.groupBy { it }
						.mapValues {
							it.value.size
						}
				}[column]

			val key = if ((columnEntry['1']!! compareTo columnEntry['0']!!) == -1) {
				'1'
			} else {
				'0'
			}

			co2rating =
				co2rating.filter {
					it[column] == key
				}.toMutableList()

			column += 1
		}

		return (" ${Integer.parseInt(co2rating.first(), 2) * Integer.parseInt(bufferOxygen.first(), 2)}")
	}
}