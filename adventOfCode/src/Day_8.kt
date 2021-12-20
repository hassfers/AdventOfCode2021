package com.mypackage

import java.lang.RuntimeException

class Day_8 : Day {
	override val day: DayIdentifier
		get() = DayIdentifier.Day_8
	override val example: Array<String>
		get() = arrayOf(
			"be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe ",
			"edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc ",
			"fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg ",
			"fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb ",
			"aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea ",
			"fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb ",
			"dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe ",
			"bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef ",
			"egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb ",
			"gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"
		)
	override val isRunningExample: Boolean
		get() = false

	override fun solvePartOne(input: Array<String>): String {
		val endings = input.map {
			it
				.substringAfter("|")
				.split(" ")
				.filter { it.isNullOrBlank().not() }
				.count {
					it.count() == 4 ||
							it.count() == 2 ||
							it.count() == 3 ||
							it.count() == 7
				}
		}
		return endings.sum().toString()
	}

	override fun solvePartTwo(input: Array<String>): String {
//		val testString = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab |\n" +
//				"cdfeb fcadb cdfeb cdbaf"
		val partOne = input.map {
			it.substringBefore("|").trim().split(" ").toTypedArray() to
					it.substringAfter("|").trim().split(" ").toTypedArray()
		}.map {
			val display = sevenSegment()
//			println(it.first.joinToString())
			display.figureOutSegments(it.first)
//			println(it.second.joinToString())
			display.decode(it.second)

		}
//		val display = sevenSegment()
//		display.figureOutSegments(partOne)
//		return display.decode(testString.substringAfter("|").trim().split(" ").toTypedArray())
		return partOne.sum().toString()
	}

	class sevenSegment {
		fun figureOutSegments(input: Array<String>) {
			val sortedinput = input.map { it.toStringArray().sorted() }.map { it.toConnectedString() }
			val one = sortedinput.filter { it.count() == 2 }.first().toStringArray().toList()
			this.one = one.toConnectedString()
//		segment.upRight = one
//		segment.downRight = one
			val eight = sortedinput.filter { it.count() == 7 }.first().toStringArray().toList()
			this.eight = eight.toConnectedString()
			val seven = sortedinput.filter { it.count() == 3 }.first().toStringArray().toList()
			this.seven = seven.toConnectedString()
			val four = sortedinput.filter { it.count() == 4 }.first().toStringArray().toList()
			this.four = four.toConnectedString()
			roof = seven.subtract(one).first()
			potentionaldownRight = one
			potentionalupRight = one
			potentionalmiddle = four.subtract(one).toList()
			potentionalupLeft = four.subtract(one).toList()
			val three = sortedinput.first {
				it.containsAllChars(one.toConnectedString())
						&& it.count() == 5
			}.toStringArray()
			this.three = three.toList().toConnectedString()
			potentionalbuttom = three.subtract(seven).subtract(four).toList()
			potentionalupLeft = potentionalmiddle.subtract(three.toList()).toList()
			potentionalmiddle = potentionalmiddle.subtract(potentionalupLeft).toList()
			val five = sortedinput.first {
				it.containsAllChars(potentionalmiddle.toConnectedString())
						&& it.contains(roof.toString())
						&& it.contains(potentionalupLeft.toConnectedString())
						&& it.contains(potentionalbuttom.toConnectedString())
						&& it.count() == 5
			}.toStringArray()
			this.five = five.toList().toConnectedString()
			potentionaldownRight = potentionaldownRight.intersect(five.toList()).toList()
			potentionalupRight = potentionalupRight.subtract(potentionaldownRight.toList()).toList()
			potentionaldownLeft = eight.subtract(five.toList()).subtract(one).toList()

			zero = eight.subtract(potentionalmiddle).toList().toConnectedString()
			six = eight.subtract(potentionalupRight).toList().toConnectedString()
			nine = eight.subtract(potentionaldownLeft).toList().toConnectedString()
			two = eight.subtract(potentionalupLeft).subtract(potentionaldownRight).toList().toConnectedString()
		}

		private var roof = ""
		private var potentionalupRight = emptyList<String>()
		private var potentionalupLeft = emptyList<String>()
		private var potentionalmiddle = emptyList<String>()
		private var potentionaldownRight = emptyList<String>()
		private var potentionaldownLeft = emptyList<String>()
		private var potentionalbuttom = emptyList<String>()

		var one = ""
		var eight = ""
		var four = ""
		var seven = ""
		var three = ""
		var five = ""
		var zero = ""
		var six = ""
		var nine = ""
		var two = ""

		private fun decode(string: String): Int {
			return when (string) {
				one -> {
					1
				}
				eight -> {
					8
				}
				four -> {
					4
				}
				seven -> {
					7
				}
				three -> {
					3
				}
				five -> {
					5
				}
				zero -> {
					0
				}
				six -> {
					6
				}
				nine -> {
					9
				}
				two -> {
					2
				}
				else -> {
					throw RuntimeException()
				}
			}
		}

		fun decode(input: Array<String>): Int {
			if (potentionalupRight.size != 1 ||
				potentionalupLeft.size != 1 ||
				potentionalmiddle.size != 1 ||
				potentionaldownRight.size != 1 ||
				potentionaldownLeft.size != 1 ||
				potentionalbuttom.size != 1
			) {
				throw Exception("da stimmt was net")
			}
			val numbers = input.map {
				"${decode(it.toStringArray().sorted().toConnectedString())}"
			}.toConnectedString()
			return numbers.toInt()
		}
	}

}