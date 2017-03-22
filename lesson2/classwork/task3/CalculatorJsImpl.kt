package kss.tasks.pdf2.practice.task3

import javax.script.ScriptEngineManager


class CalculatorJsImpl : Calculator {

    val factory = ScriptEngineManager()
    val engine = factory.getEngineByName("JavaScript")

    override fun calculate(expression: String): Number {
        return engine.eval(expression) as Number
    }

}
