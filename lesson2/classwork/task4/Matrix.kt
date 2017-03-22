package kss.tasks.pdf2.practice.task4

class Matrix private constructor(val width: Int, val height: Int, val values: Array<Array<Number>>) {

    constructor(w: Int, h: Int) : this(w, h, Array(h) { Array<Number>(w, { 0.0 }) })

    constructor (vararg values: Array<Number>) : this(values[0].size, values.size, Array(values.size) { values[it] })

    private fun setValue(row: Int, column: Int, value: Number) {
        values[row][column] = value
    }

    fun getValue(row: Int, column: Int): Number {
        if (row in 0..(height - 1) && column in 0..(width - 1))
            return values[row][column]
        throw Exception("Row: $row height $height column: $column width: $width")
    }

    operator fun times(b: Matrix): Matrix {
        val a = this
        if (a.width != b.height) {
            throw Exception("MLML.Matrix [1] height not equal to matrix [2] width")
        } else {
            val m = Matrix(a.height, b.width)
            for (i in 0..a.height - 1) {
                for (k in 0..b.width - 1) {
                    val finalI = i
                    val finalK = k
                    m.values[i][k] = (0..a.width-1).sumByDouble { j -> a.values[finalI][j].toDouble() * b.values[j][finalK].toDouble() }
                }
            }
            return m
        }
    }

    operator fun div(b: Number): Matrix {
        return times(1 / b.toDouble())
    }

    operator fun times(b: Number): Matrix {
        val a = this
        val m = Matrix(a.width, a.height)
        for (i in 0..a.height - 1) {
            for (k in 0..a.width - 1) {
                m.values[i][k] = a.values[i][k].toDouble() * b.toDouble()
            }
        }
        return m
    }

    fun add(b: Matrix): Matrix {
        val a = this
        if (a.width != b.width || a.height != b.height) {
            throw Exception("Matrix [1] size not equal to matrix [2] size")
        } else {
            val m = Matrix(a.height, a.width)
            for (i in 0..a.height - 1) {
                for (k in 0..a.width - 1) {
                    m.values[i][k] = a.getValue(i, k).toDouble() + b.getValue(i, k).toDouble()
                }
            }
            return m
        }
    }

    operator fun unaryMinus(): Matrix {
        val a = this
        val m = Matrix(a.width, a.height)
        for (i in 0..a.height - 1) {
            for (k in 0..a.width - 1) {
                m.values[i][k] = -a.values[i][k].toDouble()
            }
        }
        return m
    }

    operator fun minus(b: Matrix): Matrix {
        return add(-b)
    }

    fun transpose(): Matrix {
        val f = this
        val r = Matrix(f.height, f.width)
        for (i in 0..f.width - 1) {
            for (j in 0..f.height - 1) {
                r.setValue(i, j, f.getValue(j, i))
            }
        }
        return r
    }

    private fun longize(value: Number, length: Int): String {
        val vts = value.toString()
        val res = StringBuilder()
        (1..(length - vts.length) / 2).forEach { res.append(' ') }
        res.append(vts)
        while (res.length < length) {
            res.append(' ')
        }
        return res.toString()
    }

    private fun StringBuilder.hline(maxItemWidth: Int, a: Char, b: Char, c: Char) {
        append(a)
        for (j in 1..width) {
            for (k in 1..maxItemWidth) {
                append('─')
            }
            if (j != width) {
                append(b)
            }
        }
        append(c)
        append('\n')
    }

    override fun toString(): String {
        var maxItemWidth = 0
        for (i in 0..height - 1) {
            for (j in 0..width - 1) {
                maxItemWidth = Math.max(maxItemWidth, getValue(i, j).toString().length + 2)
            }
        }
        val res = StringBuilder()
        res.hline(maxItemWidth, '┌', '┬', '┐')
        for (i in 0..height - 1) {
            res.append("│")
            for (j in 0..width - 1) {
                res.append(longize(getValue(i, j), maxItemWidth)).append('│')
            }
            res.append('\n')
            if (i != height - 1) {
                res.hline(maxItemWidth, '├', '┼', '┤')
            }
        }
        res.hline(maxItemWidth, '└', '┴', '┘')
        return res.toString()
    }


    fun mirrorVertical(): Matrix {
        val f = this
        val r = Matrix(f.width, f.height)
        for (i in 0..f.height - 1) {
            for (j in 0..f.width - 1) {
                r.setValue(i, j, f.getValue(f.height - i - 1, j))
            }
        }
        return r
    }

    fun mirrorHorizontal(): Matrix {
        val f = this
        val r = Matrix(f.width, f.height)
        for (i in 0..f.height - 1) {
            for (j in 0..f.width - 1) {
                r.setValue(i, j, f.getValue(i, f.width - j - 1))
            }
        }
        return r
    }

    fun rotateClockwise(): Matrix {
        return rotateCounterClockwise().rotateCounterClockwise().rotateCounterClockwise()
    }

    fun rotateCounterClockwise(): Matrix {
        val f = this
        val r = Matrix(f.height, f.width)
        for (i in 0..f.height - 1) {
            for (j in 0..f.width - 1) {
                r.setValue(j, i, f.getValue(i, f.width - j - 1))
            }
        }
        return r
    }


    fun getAdditionalMinor(row: Int, column: Int): Matrix {
        val m = this
        if (m.width != m.height) {
            throw Exception("Additional minor is defined for square matrices only")
        }
        val res = Matrix(m.height - 1, m.width - 1)
        for (i in 0..m.height - 1 - 1) {
            for (j in 0..m.width - 1 - 1) {
                var newY = i
                if (newY >= row) newY++
                var newX = j
                if (newX >= column) newX++
                res.setValue(i, j, m.getValue(newY, newX))

            }
        }
        return res
    }

    fun getDeterminant(): Number {
        val m = this
        if (m.width != m.height) {
            Exception("Determinant is defined for square matrices only").printStackTrace()
            return -1.0
        }
        if (m.width == 1) {
            return m.getValue(0, 0)
        }
        if (m.width == 2) {
            return m.getValue(0, 0).toDouble() * m.getValue(1, 1).toDouble() - m.getValue(0, 1).toDouble() * m.getValue(1, 0).toDouble()
        }
        return (0..m.width-1).sumByDouble { j ->
            Math.pow(-1.0, j.toDouble()) *
                    m.getValue(0, j).toDouble() *
                    m.getAdditionalMinor(0, j).getDeterminant().toDouble()
        }
    }

}
