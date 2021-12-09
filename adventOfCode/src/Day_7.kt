package com.mypackage

import kotlin.math.abs

class Day_7 : Day {
    override val day: DayIdentifier
        get() = DayIdentifier.Day_7
    override val example: Array<String>
        get() = arrayOf("16,1,2,0,4,2,7,1,2,14")
    override val isRunningExample: Boolean
        get() = false

    override fun solvePartOne(input: Array<String>): String {
        val ints = input.first()
            .split(",")
            .map { it.toInt() }
            .toIntArray()

        val test = ints.toSet()
            .minByOrNull {
                numberOfOperationsToWithLinearFuel(ints, it)
            }
        return numberOfOperationsToWithLinearFuel(ints, test!!).toString()
    }

    override fun solvePartTwo(input: Array<String>): String {
        val ints = input.first()
            .split(",")
            .map { it.toInt() }
            .toIntArray()

        val test = (ints.minOrNull()!!..ints.maxOrNull()!!).toSet()
            .minByOrNull {
                numberOfOperationsToWithExponentialFuel(ints, it)
            }
        return numberOfOperationsToWithExponentialFuel(ints, test!!).toString()
    }

    fun numberOfOperationsToWithLinearFuel(start: IntArray, position: Int): Int {
        val numbers = start.map { number ->
            number to start.count {
                it == number
            }
        }
            .toSet()
            .toMap()

        val number = start.toSet()
            .fold(0, ({ acc: Int, i: Int ->
                //            println(acc)
                acc + (abs(i - position) * numbers[i]!!)
            }))

        return number
    }

    fun numberOfOperationsToWithExponentialFuel(start: IntArray, position: Int): Int {
        val numbers = start.map { number ->
            number to start.count {
                it == number
            }
        }
            .toSet()
            .toMap()

        val number = start.toSet()
            .fold(0, ({ acc: Int, i: Int ->
                //            println(acc)
                acc + ((1 until abs(i - position) + 1).sum() * numbers[i]!!)
            }))

        return number
    }
}
