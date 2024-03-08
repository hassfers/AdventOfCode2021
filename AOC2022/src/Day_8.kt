package com.mypackage.AOC2022

class Day_8: Day() {
	override val example: List<String>
		get() = listOf(
			"30373",
			"25512",
			"65332",
			"33549",
			"35390"
		)

	override val isRunningExample: Boolean
		get() = true

	enum class VisibilityDirection {
		left, right, up, down
	}

	inner class Tree(val height: Int) {
		var leftTrees = emptyList<Tree>()
		var rightTrees = emptyList<Tree>()
		var upTrees = emptyList<Tree>()
		var downTrees = emptyList<Tree>()
		var standingOnEdge = false
	}


	override fun solvePartOne(input: List<String>): String {
		val trees = input.map { it.map { Tree("$it".toInt()) } }
		val transposeTrees = trees.genericTranspose()

		trees.forEachIndexed { index, list ->
			if (index == 0 || index == (input.size - 1 )) {
				list.forEach {
					it.standingOnEdge = true
				}
			} else {
				list.forEachIndexed { treeindex, tree ->
					tree.leftTrees = list.subList(0,treeindex)
					tree.rightTrees = list.subList(treeindex+1,list.size)
				}
			}
		}

		return super.solvePartOne(input)
	}
}