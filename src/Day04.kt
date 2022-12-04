fun main() {
    fun sectionsData(it: String): List<Int> {
        return it.split(",").map{
            it.split("-").map {
                it.toInt()
            }
        }.flatten()
    }

    fun part1(input: List<String>): Int {
        return input.count {
            val (a, b, c, d) = sectionsData(it)
            (a <= c && b >= d) || (c <= a && d >= b)
        }
    }

    fun part2(input: List<String>): Int {
        return input.count {
            val (a, b, c, d) = sectionsData(it)
            a <= d && b >= c
        }
    }

    val inputData = readInput("Day04_input")
    println("Part 1: " + part1(inputData))
    println("Part 2: " + part2(inputData))
}
