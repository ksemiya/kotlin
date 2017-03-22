package kss.tasks.pdf2.practice.task1

class LanguageBuilder {

    fun buildLanguage(helloWorldMessage: () -> String): Language {
        return object : Language {
            override fun buildHelloWorldMessage() = helloWorldMessage()
        }
    }

    fun buildLanguage(helloWorldMessage: String): Language {
        return object : Language {
            override fun buildHelloWorldMessage() = helloWorldMessage
        }
    }

}