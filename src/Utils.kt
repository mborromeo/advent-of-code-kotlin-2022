import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Reads lines from a given input txt file, split by empty lines
 */
fun readInputGroups(name: String) = File("src", "$name.txt").readText().split("\n\n")

/***
 * Reads the entire input file as a single string.
 */
fun readInputText(name: String) = File("src", "$name.txt").readText()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
