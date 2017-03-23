package kotlin.lesson2.homework.rendering

import java.awt.Graphics2D
import java.awt.Point

interface RenderingContext {

    fun translateVertical(y: Int): Int

    fun translateHorizontal(x: Int): Int

    fun translatePoint(p: Point): Point

    fun graphics(): Graphics2D

}