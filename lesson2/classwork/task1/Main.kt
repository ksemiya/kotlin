package kss.tasks.pdf2.practice.task1

import java.util.*

fun main(args: Array<String>) {

    val languageBuilder = LanguageBuilder()
    val helloWorldBuilder = HelloWorldBuilder()

    val englishLanguage = languageBuilder.buildLanguage("Hello world!")
    val helloWorld = helloWorldBuilder.buildHelloWorldPrinter(englishLanguage)
    helloWorld.printMessage()

    val englishDatedLanguage = languageBuilder.buildLanguage { "Hello, world, at ${Date(System.currentTimeMillis())}" }
    val helloWorldDated = helloWorldBuilder.buildHelloWorldPrinter(englishDatedLanguage)
    helloWorldDated.printMessage()
    Thread.sleep(10000)
    helloWorldDated.printMessage()

}
