package kss.tasks.pdf2.practice.task1

abstract class HelloWorldPrinter: MessagePrinter {

    abstract protected fun getMessage(): String

    override fun printMessage() {
        println(getMessage())
    }

}