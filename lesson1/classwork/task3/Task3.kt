package kotlin.lesson1.classwork.task3

private fun buildPascalCoefs(n: Int): ArrayList<ArrayList<Long>> {
    val list = ArrayList<ArrayList<Long>>()
    for (i in 1..n) {
        val local = ArrayList<Long>()
        for (j in 1..i) {
            if (j == 1 || j == i) {
                local.add(1L)
            } else {
                local.add(list[i - 2][j - 2] + list[i - 2][j - 1])
            }
        }
        list.add(local)
    }
    return list
}

fun Long.prettyfy(length: Int): String {
    val string = toString()
    val add = string.length - length
    val builder = StringBuilder(length)
    (1..add / 2).forEach { builder.append(' ') }
    builder.append(string)
    while (builder.length < length) {
        builder.append(' ')
    }
    return builder.toString()
}

private fun printSpaces(count: Int) {
    for (i in 1..count) {
        print(' ')
    }
}

fun printPascalTriangle(n: Int) {

    val coefs = buildPascalCoefs(n)
    val length = coefs.flatten().max().toString().length + 2
    val maxLineLength = coefs.lastOrNull()?.size ?: 0 * length

    coefs.forEach {
        printSpaces((maxLineLength - it.size * length) / 2)
        it.map { it.prettyfy(length) }.forEach(::print)
        println()
    }

}

fun main(args: Array<String>) {
    printPascalTriangle(20)
}