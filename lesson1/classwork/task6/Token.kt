package kss.tasks.pdf1.practice.task6

abstract class Token() {

    abstract fun getValue(): Value

}

class RawToken(val name: String) : Token() {
    override fun getValue(): Value {
        try {
            return LongValue(name.toLong())
        } catch (e: NumberFormatException) {/*ignore*/
        }
        try {
            return DoubleValue(name.toDouble())
        } catch (e: NumberFormatException) {/*ignore*/
        }
        throw Task6UnexpectedTokenException("Unexpected token, expected was number, but found " + name)
    }
}

abstract class BinaryOperationToken(val left: Token, var right: Token) : Token()

class BinaryPlusToken(left: Token, right: Token) : BinaryOperationToken(left, right) {
    override fun getValue() = left.getValue().plus(right.getValue())
}

class BinaryMinusToken(left: Token, right: Token) : BinaryOperationToken(left, right) {
    override fun getValue() = left.getValue().minus(right.getValue())
}

class BinaryDivideToken(left: Token, right: Token) : BinaryOperationToken(left, right) {
    override fun getValue() = left.getValue().divide(right.getValue())
}

class BinaryMultiplyToken(left: Token, right: Token) : BinaryOperationToken(left, right) {
    override fun getValue() = left.getValue().multiple(right.getValue())
}

abstract open class UnaryOperationToken(val value: Token) : Token()

class UnaryMinusToken(value: Token) : UnaryOperationToken(value) {
    override fun getValue() = value.getValue().unaryMinus()
}

class BracketsToken(val value: Token) : Token() {
    override fun getValue() = value.getValue()
}