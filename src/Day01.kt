fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it -> it.split("\n").sumOf { it.toInt() } }.max()
    }

    fun part2(input: List<String>): Int {
        return input.map { it -> it.split("\n").sumOf { it.toInt() } }.sortedDescending().take(3).sum()
    }

    val inputData = readInputGroups("Day01_input")
    println("Part 1: " + part1(inputData))
    println("Part 2: " + part2(inputData))
}
