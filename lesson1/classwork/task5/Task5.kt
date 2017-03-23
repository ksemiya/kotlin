package kotlin.lesson1.classwork.task5

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

private val alphabet = ('a'..'z').toList()

fun generateWord(random: Random): String {
    val length = random.nextInt(3) + 3
    val builder = StringBuilder(length)
    (1..length).forEach {
        builder.append(alphabet[random.nextInt(alphabet.size)])
    }
    return builder.toString()
}

fun generateWords(size: Int): ArrayList<String> {
    val random = Random()
    return (1..size).map { generateWord(random) }.toCollection(ArrayList())
}

fun duplicatesStat(list: List<String>) {

    val repeats = HashMap<String, ArrayList<Int>>()
    list.forEachIndexed { index, list ->
        repeats.getOrPut(list, { ArrayList() }).add(index)
    }
    println("Unique: " + repeats.entries.sortedBy { it.value[0] }.map { it.key })
    println("Repeats:\n" + repeats.entries
            .filter { it.value.size > 1 }
            .map { "${it.key} (${it.value.size} repeats at indices ${it.value})\n" }
            .reduce { a, b -> a + b }
    )

}

fun main(args: Array<String>) {
    duplicatesStat(generateWords(1024))
}