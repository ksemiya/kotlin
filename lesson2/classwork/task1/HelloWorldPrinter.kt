package kotlin.lesson2.classwork.task1

abstract class HelloWorldPrinter: MessagePrinter {

    abstract protected fun getMessage(): String

    override fun printMessage() {
        println(getMessage())
    }

}