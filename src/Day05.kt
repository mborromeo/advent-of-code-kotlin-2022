fun main() {
    fun parseCrates(input: List<String>): List<MutableList<Char>> {
        val crates: List<MutableList<Char>> = List(input.last().last().digitToInt()) { mutableListOf() }

        for (i in 1 until input.size){
            input.reversed()[i].chunked(4).forEachIndexed { index, s ->
                val crateLetter = s.replace("\\s|\\[|\\]".toRegex(), "")
                if (crateLetter.isNotEmpty()) {
                    crates[index].add(crateLetter.first())
                }
            }
        }

        return crates
    }

    fun parseInput(input: List<String>): Pair<List<MutableList<Char>>, List<String>> {
        var isState = true
        val (state, commands) = input.partition {
            if (it.isBlank()) { isState = false }
            isState
        }

        return Pair(parseCrates(state), commands.filter { it.isNotEmpty() })
    }

    fun parseCommand(command: String): Triple<Int, Int, Int> {
        val (amount, from, to) = command.split(" ").slice(setOf(1, 3, 5)).map { it.toInt() }
        return Triple(amount, from, to)
    }

    fun part1(input: List<String>): String {
        val (crates, commands) = parseInput(input)

        for (command in commands) {
            val (amount, from, to) = parseCommand(command)
            repeat (amount) {
                crates[to - 1].add(crates[from - 1].removeLast())
            }
        }

        return crates.map {x -> x.lastOrNull() ?: " "}.joinToString("", "", "")
    }

    fun part2(input: List<String>): String {
        val (crates, commands) = parseInput(input)

        for (command in commands) {
            val (amount, from, to) = parseCommand(command)
            val tail = crates[from - 1].takeLast(amount)
            repeat (amount) {
                crates[from - 1].removeLast()
            }
            crates[to - 1].addAll(tail)
        }

        return crates.map {x -> x.lastOrNull() ?: " "}.joinToString("", "", "")
    }

    val input = readInput("Day05_input")
    println(part1(input))
    println(part2(input))
}