fun main() {
    val pointsPerShape = mapOf(
        "X" to 1,
        "Y" to 2,
        "Z" to 3
    )

    fun part1(input: List<String>): Int {
        val pointsPerOutcome = mapOf(
            "AX" to 3,
            "BY" to 3,
            "CZ" to 3,
            "AY" to 6,
            "BZ" to 6,
            "CX" to 6
        )

        return input.sumOf { it -> it.split(' ').let { pointsPerShape.getOrDefault(it[1], 0) + pointsPerOutcome.getOrDefault(it[0] + it[1], 0) } }
    }

    fun part2(input: List<String>): Int {
        val selectShape = mapOf(
            "X" to mapOf(
                "A" to "Z",
                "B" to "X",
                "C" to "Y"
            ),
            "Y" to mapOf(
                "A" to "X",
                "B" to "Y",
                "C" to "Z"
            ),
            "Z" to mapOf(
                "A" to "Y",
                "B" to "Z",
                "C" to "X"
            )
        )

        val pointsPerRiggedOutcome = mapOf(
            "Y" to 3,
            "Z" to 6
        )

        return input.sumOf { it -> it.split(' ').let {
            pointsPerShape.getOrDefault(selectShape.getOrDefault(it[1], mapOf()).getOrDefault(it[0], 0), 0) + pointsPerRiggedOutcome.getOrDefault( it[1], 0) }
        }
    }

    val inputData = readInput("Day02_input")
    println("Part 1: " + part1(inputData))
    println("Part 2: " + part2(inputData))
}
