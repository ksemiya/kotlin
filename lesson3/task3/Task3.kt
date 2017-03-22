package kss.tasks.freetasks.task3

fun task3(list: List<Int>, set: Set<Int>): List<List<Int>> {
    return set
            .map { valueFromSet ->
                list
                        .mapIndexed { index, listItem -> index to listItem }
                        .filter { it.second==valueFromSet }
                        .map { it.first }
            }
}

