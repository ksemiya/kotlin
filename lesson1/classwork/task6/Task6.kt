package kss.tasks.pdf1.practice.task6

private fun tokenize(s: String, tokenz: Collection<Char>): ArrayList<String> {
    val list = ArrayList<String>()
    val lastBuilder = StringBuilder()
    for (c in s) {
        if (c in tokenz) {
            if (lastBuilder.isNotEmpty()) {
                list.add(lastBuilder.toString())
                lastBuilder.setLength(0)
            }
            list.add(c.toString())
        } else {
            lastBuilder.append(c)
        }
    }
    if (lastBuilder.isNotEmpty()) {
        list.add(lastBuilder.toString())
    }
    return list
}

class TokenReader(val s: List<String>) {

    var index = 0

    fun hasNext(): Boolean {
        return index < s.size
    }

    fun next(): String {
        val value = s[index]
        index++
        return value
    }

    fun unroll() {
        index--
    }

}

fun readStartToken(s: TokenReader): Token {
    val token = s.next()
    if (token == "-") {
        return UnaryMinusToken(readStartToken(s))
    }
    if (token == "(") {
        val res = readOpTokens(s)
        val closeBracketToken = if (s.hasNext()) s.next() else "[end of line]"
        if (closeBracketToken != ")") {
            throw Task6UnexpectedTokenException("Expected closing bracket, but found " + closeBracketToken)
        }
        return BracketsToken(res)
    }
    return RawToken(token)
}

fun readOpTokens(s: TokenReader): Token {
    var lastToken: Token = readStartToken(s)
    while (s.hasNext()) {
        if (s.hasNext()) {
            val operation = s.next()
            if (operation == ")") {
                s.unroll()
                return lastToken
            }
            if (lastToken is BinaryPlusToken || lastToken is BinaryMinusToken) {
                if (operation == "*") {
                    (lastToken as BinaryOperationToken).right = BinaryMultiplyToken(lastToken.right, readStartToken(s))
                    continue
                }
                if (operation == "/") {
                    (lastToken as BinaryOperationToken).right = BinaryDivideToken(lastToken.right, readStartToken(s))
                    continue
                }
            }
            if (operation == "+") {
                lastToken = BinaryPlusToken(lastToken, readStartToken(s))
                continue
            }
            if (operation == "-") {
                lastToken = BinaryMinusToken(lastToken, readStartToken(s))
                continue
            }
            if (operation == "*") {
                lastToken = BinaryMultiplyToken(lastToken, readStartToken(s))
                continue
            }
            if (operation == "/") {
                lastToken = BinaryDivideToken(lastToken, readStartToken(s))
                continue
            }
        }
    }
    return lastToken
}

fun execute(s: String): Value {
    val safeString = s.replace(Regex("[ \t]+"), "")
    val rawTokens = tokenize(safeString, listOf('+', '-', '*', '/', '(', ')'))
    val root = readOpTokens(TokenReader(rawTokens))
    return root.getValue()
}

fun main(args: Array<String>) {
    println(execute("5 / 23 +- 4 + 4 * 5"))
}