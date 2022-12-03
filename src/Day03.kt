fun main() {
    fun priority(el: Char): Int {
        return when (el) {
            in 'a'..'z' -> el - 'a' + 1
            in 'A'..'Z' -> el - 'A' + 27
            else -> 0
        }
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { rucksack ->
            priority((rucksack.take(rucksack.length / 2).toSet() intersect rucksack.drop(rucksack.length / 2).toSet()).single())
        }
    }

    fun part2(input: List<String>): Int {
        val groups = input.chunked(3)
        return groups.sumOf { rucksacks ->
            priority(rucksacks.map { it.toSet() }.reduce { commonItems, content -> commonItems intersect content }.single())
        }
    }

    val inputData = readInput("Day03_input")
    println("Part 1: " + part1(inputData))
    println("Part 2: " + part2(inputData))
}