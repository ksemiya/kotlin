package kss.tasks.freetasks.task2

fun task2(source: String) {
    println(
            if (
            (0..source.length / 2)
                    .map { source[it] != source[source.length - it - 1] }
                    .reduce { a, b -> a || b }
                    ) "нет" else "да"
    )
}