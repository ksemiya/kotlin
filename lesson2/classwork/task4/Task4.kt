package kss.tasks.pdf2.practice.task4

private fun arrayOfNumber(vararg n: Number): Array<Number> {
    return Array(n.size, { n[it] })
}

fun rotationDemo() {
    val matrix1 = Matrix(
            arrayOfNumber(0, 1, 2, 3),
            arrayOfNumber(1, 1, 4, 3),
            arrayOfNumber(2, 8, 2, 3)
    )
    println(matrix1)

    val matrix2 = matrix1.rotateCounterClockwise()
    println(matrix2)

    val matrix3 = matrix1.rotateClockwise()
    println(matrix3)
}

fun mirrorDemo() {
    val matrix1 = Matrix(
            arrayOfNumber(0, 1, 2, 3),
            arrayOfNumber(1, 1, 4, 3),
            arrayOfNumber(2, 8, 2, 3)
    )
    println(matrix1)

    val matrix2 = matrix1.mirrorHorizontal()
    println(matrix2)

    val matrix3 = matrix1.mirrorVertical()
    println(matrix3)
}

fun transposeDemo() {
    val matrix1 = Matrix(
            arrayOfNumber(0, 1, 2, 3),
            arrayOfNumber(1, 1, 4, 3),
            arrayOfNumber(2, 8, 2, 3)
    )
    println(matrix1)

    val matrix2 = matrix1.transpose()
    println(matrix2)
}

fun determinantDemo() {
    val matrix1 = Matrix(
            arrayOfNumber(0, 1, 2, 3),
            arrayOfNumber(1, 1, 4, 3),
            arrayOfNumber(2, 8, 2, 3),
            arrayOfNumber(3, 8, 2, 21)
    )
    println(matrix1)

    val det = matrix1.getDeterminant()
    println(det)
}

fun main(args: Array<String>) {
    rotationDemo()
    mirrorDemo()
    transposeDemo()
    determinantDemo()
}