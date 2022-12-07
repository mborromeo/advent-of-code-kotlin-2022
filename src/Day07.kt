fun main() {
    class File(val size: Int)

    class Directory(val name: String) {
        private val directories: MutableList<Directory> = mutableListOf()
        private val files: MutableList<File> = mutableListOf()

        fun size(): Int = files.sumOf { it.size } + directories.sumOf { it.size() }
        fun addDirectory(directoryName: String, path: List<String>) =
            getDirectoryAtPath(path).addDirectory(Directory(directoryName))

        fun addFile(file: File, path: List<String>) = getDirectoryAtPath(path).addFile(file)
        fun directoriesFlattened(): Set<Directory> =
            setOf(this) + directories + directories.flatMap { it.directoriesFlattened() }

        private fun addDirectory(directory: Directory) = directories.add(directory)
        private fun addFile(file: File) = files.add(file)
        private fun getDirectoryAtPath(path: List<String>) =
            path.fold(this) { acc, str -> acc.directories.first { it.name == str } }
    }

    fun fileSystem(input: List<String>): Directory {
        val fs = Directory("/")
        val cp: MutableList<String> = mutableListOf()

        input.drop(1).map {
            when {
                it.any { c -> c.isDigit() } -> { fs.addFile(File(it.split(" ").first().toInt()), cp) }
                it.contains("dir") -> { fs.addDirectory(it.substring(4), cp) }
                it.contains("cd") && it.contains("..") -> { cp.removeLast() }
                it.contains("cd") && !it.contains("..") -> { cp.add(it.substring(5)) }
                else -> {}
            }
        }

        return fs
    }

    fun part1(input: List<String>): Int = fileSystem(input).directoriesFlattened().map { it.size() }.filter { it <= 100000 }.sum()

    fun part2(input: List<String>): Int {
        val toBeRemoved = fileSystem(input).directoriesFlattened().map{ it.size() }.max() - 40000000
        return fileSystem(input).directoriesFlattened().map { it.size() }.filter { it >= toBeRemoved }.minBy { it }
    }

    val input = readInput("Day07_input")
    println(part1(input))
    println(part2(input))
}