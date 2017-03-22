package kss.tasks.pdf2.belochki.models

import java.awt.Color

class TimedColor(private val color: Color) {

    fun get(time: Double): Color {
        return color
    }

}