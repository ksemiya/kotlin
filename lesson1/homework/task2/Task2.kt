package kotlin.lesson1.homework.task2


private fun StringBuilder.append(symbol: Char, count: Int, dropOneSymbolsCount: Boolean) {
    if (count > 0) {
        append(symbol)
        if (!(dropOneSymbolsCount && count == 1)) {
            append(count)
        }
    }
}

fun task2(data: String, dropOneSymbolsCount: Boolean = false): String {
    var lastSymbol = '0'
    var count = 0
    val result = StringBuilder(data.length)
    for (c in data) {
        if (c in '0'..'9') {
            throw Task2UnexpectedStringException("String contains digit characters so cannot be encoded")
        }
        if (lastSymbol != c) {
            result.append(lastSymbol, count, dropOneSymbolsCount)
            lastSymbol = c
            count = 1
        } else {
            count++
        }
    }
    result.append(lastSymbol, count, dropOneSymbolsCount)
    return result.toString()
}