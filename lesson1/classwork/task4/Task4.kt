package kotlin.lesson1.classwork.task4

import java.util.*

fun generateRandomList(size: Int): List<Double> {
    val random = Random()
    return (1..size).map { random.nextDouble() }.toCollection(ArrayList(size))
}

fun getMedianAvg(raw: List<Double>): Pair<Double, Double> {
    val list = raw.sorted()
    val median = if (list.size % 2 == 0) {
        (list[list.size / 2 - 1] + list[list.size / 2]) / 2
    } else {
        list[list.size / 2]
    }
    val avg = list.sum() / list.size
    return median to avg
}

fun main(args: Array<String>) {

    val size = 20
    val (median, avg) = getMedianAvg(generateRandomList(size).sorted())
    println("Median: $median; avg: $avg")

}
