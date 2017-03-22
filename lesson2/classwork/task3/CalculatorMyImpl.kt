package kss.tasks.pdf2.practice.task3

import kss.tasks.pdf1.practice.task6.execute

class CalculatorMyImpl: Calculator {

    override fun calculate(expression: String): Number {
        return execute(expression).t
    }

}
