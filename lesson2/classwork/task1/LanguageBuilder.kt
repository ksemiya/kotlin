package kotlin.lesson2.classwork.task1

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