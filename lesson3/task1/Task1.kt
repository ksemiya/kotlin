package kss.tasks.freetasks.task1

import java.util.*

/*
на вход программы приходит list целых.
Программа считает среднее для n выбранных элементов коллекции.
Затем случайно перемешивает элементы списка, считает среднее для n элементов на тех же позициях, что были выбраны в первый раз.
Такая процедура повторяется n раз.
Затем вычисляется среднее по всем замерам.
 */

fun task1(data: List<Int>, selectedPositions: List<Int>): Double {
    if (data.isEmpty()) throw Task1WrongDataException("Source set cannot be empty")
    if (selectedPositions.isEmpty()) throw Task1WrongIndicesException("Selected positions cannot be empty")
    selectedPositions
            .filter { it >= data.size }
            .forEach {
                throw Task1WrongIndicesException("Selected index cannot be more or equal to sourceset's size")
            }

    return (0..selectedPositions.size)
            .map {
                if (it != 0) {
                    Collections.shuffle(data) //not very functional :c
                }
                data
            }
            .map { collection ->
                selectedPositions
                        .map { collection[it].toLong() }
                        .sum().toDouble() / selectedPositions.size
            }
            .sum() / (selectedPositions.size + 1)
}
