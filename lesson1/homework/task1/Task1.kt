package kotlin.lesson1.homework.task1


fun task1(data: List<String>): List<List<String>> {
    data
            .forEach { string ->
                if (string.isEmpty()) throw Task1UnexpectedStringException("Empty string is not allowed")
                string
                        .map(Char::toLowerCase)
                        .forEach {
                            if (it < 'a' || it > 'z') throw Task1UnexpectedStringException("String $string contains symbol that is not in range a-z")
                        }
            }
    val map = HashMap<Char, ArrayList<String>>()
    data
            .map { it[0].toLowerCase() to it }
            .forEach {
                map
                        .getOrPut(it.first, { ArrayList() })
                        .add(it.second)
            }
    return ('a'..'z')
            .map { map.getOrDefault(it, ArrayList()) }
            .toList()
}