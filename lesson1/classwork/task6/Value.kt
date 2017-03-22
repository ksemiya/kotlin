package kss.tasks.pdf1.practice.task6

open class Value(val t: Number) {

    override fun toString(): String {
        return t.toString()
    }

    fun unaryMinus(): Value {
        if (this is DoubleValue) {
            return DoubleValue(-t.toDouble())
        }
        return LongValue(-t.toLong())
    }

    fun biOp(other: Value, onLong: (a: Long, b: Long) -> Long, onDouble: (a: Double, b: Double) -> Double): Value {
        if (this is DoubleValue || other is DoubleValue) {
            return DoubleValue(onDouble(t.toDouble(), other.t.toDouble()))
        }
        return LongValue(onLong(t.toLong(), other.t.toLong()))
    }

    fun minus(other: Value): Value {
        return biOp(other, { a, b -> a - b }, { a, b -> a - b })
    }

    fun plus(other: Value): Value {
        return biOp(other, { a, b -> a + b }, { a, b -> a + b })
    }

    fun divide(other: Value): Value {
        return biOp(other, { a, b -> a / b }, { a, b -> a / b })
    }

    fun multiple(other: Value): Value {
        return biOp(other, { a, b -> a * b }, { a, b -> a * b })
    }

}

class LongValue(t: Long) : Value(t)

class DoubleValue(t: Double) : Value(t)