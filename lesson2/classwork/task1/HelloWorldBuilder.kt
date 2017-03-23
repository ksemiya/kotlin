package kotlin.lesson2.classwork.task1


class HelloWorldBuilder {

    fun buildHelloWorldPrinter(language: Language): HelloWorldPrinter {
        return object : HelloWorldPrinter() {
            override fun getMessage()=language.buildHelloWorldMessage()
        }
    }

}