import kotlin.math.max

fun main() {
    data class Mtx(
        val l: List<Int>,
        val r: List<Int>,
        val t: List<Int>,
        val b: List<Int>,
    ) {
        val dir = listOf(l, r, t, b)
    }

    fun List<List<Int>>.build(i: Int, j: Int) = Mtx(
        l = this[i].subList(0, j).reversed(),
        r = this[i].subList(j + 1, this[i].lastIndex + 1),
        t = this.subList(0, i).map { it[j] }.reversed(),
        b = this.subList(i + 1, this.lastIndex + 1).map { it[j] }
    )

    fun part1(input: List<String>): Int {
        val forest = input.map { row -> row.map { it.digitToInt() } }
        var visibleTrees = 2 * (forest.size + forest.first().size - 2)

        for (i in 1 until forest.lastIndex) {
            for (j in 1 until forest[i].lastIndex) {
                if (forest.build(i, j).dir.map { it -> it.maxOf { it } }.any { it < forest[i][j] }) visibleTrees += 1
            }
        }

        return visibleTrees
    }

    fun part2(input: List<String>): Int {
        var maxScore = 0
        val forest = input.map { row -> row.map { it.digitToInt() } }

        for (i in 1 until forest.lastIndex) {
            for (j in 1 until forest[i].lastIndex) {
                val v = forest.build(i, j)
                val score = v.dir.map {
                        direction -> val index = direction.indexOfFirst { it >= forest[i][j] } + 1
                        index.takeIf { it != 0 } ?: direction.size
                }.reduce { acc, idx -> acc * idx }
                maxScore = max(maxScore, score)
            }
        }

        return maxScore
    }

    val input = readInput("Day08_input")
    println(part1(input))
    println(part2(input))
}