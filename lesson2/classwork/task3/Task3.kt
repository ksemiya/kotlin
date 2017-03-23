package kotlin.lesson2.classwork.task3

fun main(args: Array<String>) {

    val calc1: Calculator = CalculatorMyImpl()
    val calc2: Calculator = CalculatorJsImpl()

    println(calc1.calculate("2+2*2"))
    println(calc2.calculate("2+2*2"))

}
