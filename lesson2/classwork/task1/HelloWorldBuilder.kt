package kss.tasks.pdf2.practice.task1

class HelloWorldBuilder {

    fun buildHelloWorldPrinter(language: Language): HelloWorldPrinter {
        return object : HelloWorldPrinter() {
            override fun getMessage()=language.buildHelloWorldMessage()
        }
    }

}