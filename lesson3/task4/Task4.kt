package kss.tasks.freetasks.task4

fun task4(string: String, a: Int, b: Int): String {
    return string
            .mapIndexed { index, char -> index to char }
            .filter { it.first in a..b }
            .map { it.second.toString() }
            .reduce { a, b -> a + b }
}
