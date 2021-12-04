package com.mypackage

import java.lang.Exception

class BingoGame {
	val numbers: Array<Int>
	var boards: Array<BingoBoard>

	constructor(array: Array<String>) {
		val modifiedInputArray = array.toMutableList().splitBy("")
		numbers = array.first().split(",").map { it.toInt() }.toTypedArray()
		boards = modifiedInputArray.drop(1).map { BingoBoard(it.toTypedArray()) }.toTypedArray()
	}

	fun playWinFirst() {
		numbers.forEach { number ->
			boards.forEach { bingoBoard ->
				bingoBoard.handleIncomingNumber(number)
			}
		}
	}

	fun playWinLast() {
		numbers.forEach { number ->
			boards.forEach { bingoBoard ->
				try {
					bingoBoard.handleIncomingNumber(number)
				} catch (ex: BingoGame.BingoBoard.WinningException) {
					if (boards.size > 1) {
						boards = boards.toList().minus(bingoBoard).toTypedArray()
					} else {
						throw ex
					}
				}

			}
		}
	}

	inner class BingoBoard {
		var entries: Array<BoardEntry>
		val hasFullRow: Boolean
			get() {
				return entries.toList().chunked(5).count { it.all { it.selected } } > 0
			}

		val hasFullColum: Boolean
			get() {
				return entries.toList().chunked(5).genericTranspose().count { it.all { it.selected } } > 0
			}

		val hasWon: Boolean
			get() {
				return hasFullRow || hasFullColum
			}

		constructor(array: Array<String>) {
			entries = array
				.map { it.split("\\s".toRegex()) }
				.map {
					it.filter { it != "" }
				}.flatten()
				.map { it.toInt() }
				.map { BoardEntry(it, false) }
				.toTypedArray()
		}

		fun handleIncomingNumber(number: Int) {
			entries.find { it.number == number }?.selected = true
			if (hasWon) {
				throw WinningException(generateWinningNumber(number))
			}
		}

		fun generateWinningNumber(lastNumber: Int): Int {
			return entries.filter { it.selected.not() }.map { it.number }.sum() * lastNumber
		}

		inner class BoardEntry(val number: Int, var selected: Boolean)
		inner class WinningException(val winningScore: Int) : RuntimeException()
	}
}