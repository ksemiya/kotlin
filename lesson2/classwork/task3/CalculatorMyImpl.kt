package kotlin.lesson2.classwork.task3

import kss.tasks.pdf1.practice.task6.execute

class CalculatorMyImpl: Calculator {

    override fun calculate(expression: String): Number {
        return execute(expression).t
    }

}
