package kss.tasks.pdf1.practice.task2

import java.io.BufferedReader
import java.io.InputStreamReader

/*
I hate bubble, so let's use simple select sort
 */
private fun <T : Comparable<T>> sort(data: ArrayList<T>): ArrayList<T> {
    (0..data.size - 1).forEach {
        var minValue = data[it]
        var minIndex = it
        (it..data.size - 1).forEach {
            val localValue = data[it]
            if (localValue < minValue) {
                minValue = localValue
                minIndex = it
            }
        }
        val old = data[it]
        data[it] = minValue
        data[minIndex] = old
    }
    return data
}

fun lineToInts(line: String): ArrayList<Int> {
    val splitted = line.split(Regex("[ \t]+"))
    val data = ArrayList<Int>()
    if (splitted.size == 1 && splitted[0] == "") {
        throw Task2EmptyLineException("Passed line is empty")
    }
    for (s in splitted) {
        try {
            data.add(s.toInt())
        } catch (e: NumberFormatException) {
            throw Task2WrongIntException("Passed value $s is not a correct int")
        }
    }
    return data
}

fun main(args: Array<String>) {
    val rawData = BufferedReader(InputStreamReader(System.`in`))
    val line = rawData.readLine()
    try {
        sort(lineToInts(line)).forEach { print("$it ") }
        println()
    } catch (e: Task2EmptyLineException) {
        System.err.println("Passed line is empty :c")
    } catch (e: Task2WrongIntException) {
        System.err.println("Passed value is not a correct int :c")
    }
}