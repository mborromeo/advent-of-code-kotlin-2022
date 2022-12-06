fun main() {
    fun countCharsToProcess(input: String, markerLength: Int): Int {
        return input.indices.find {
            input.drop(it).take(markerLength).toSet().size == markerLength
        }!! + markerLength
    }

    fun part1(input: String): Int {
        return countCharsToProcess(input, 4)
    }

    fun part2(input: String): Int {
        return countCharsToProcess(input, 14)
    }

    val input = readInputText("Day06_input")
    println(part1(input))
    println(part2(input))
}