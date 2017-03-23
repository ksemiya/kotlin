package kotlin.lesson2.homework

import kss.tasks.pdf2.belochki.entities.GameEntity
import kss.tasks.pdf2.belochki.entities.TreePart
import kss.tasks.pdf2.belochki.entities.WorldContext
import kss.tasks.pdf2.belochki.entities.animals.Squirrel
import kss.tasks.pdf2.belochki.entities.trees.*
import kss.tasks.pdf2.belochki.rendering.RenderingContext
import java.awt.*
import java.util.*
import javax.swing.JFrame
import javax.swing.JPanel

class Belochki : JFrame() {

    val models1 = ArrayList<GameEntity>()
    val models2 = ArrayList<GameEntity>()

    init {

        val random = Random()
        val forestWidth = 1000
        val forestHeight = 1000
        val trees = 100

        (1..trees).forEach {
            val type = random.nextInt(5)
            val x = random.nextInt(forestWidth) - forestWidth / 2
            val y = random.nextInt(forestHeight) - forestHeight / 2
            val birch = when (type) {
                0 -> Birch(x, y)
                1 -> Spruce(x, y)
                2 -> Pine(x, y)
                3 -> Oak(x, y)
                4 -> Maple(x, y)
                else -> {
                    throw Exception("?")
                }
            }
            models1.add(birch)
            models2.add(birch)
            if (random.nextInt(10) == 0) {
                val treePart = when (random.nextInt(3)) {
                    0 -> TreePart.roots
                    1 -> TreePart.leafs
                    2 -> TreePart.trunk
                    else -> {
                        throw Exception("?")
                    }
                }
                val squirrel = Squirrel(birch, treePart)
                models1.add(squirrel)
                models2.add(squirrel)
            }
        }

        val worldContext = object : WorldContext {
            override fun getTime(): Double {
                return 15.0
            }
        }

        val jpanel = object : JPanel() {

            override fun paint(g: Graphics?) {

                val g2 = g as Graphics2D
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

                g2.color = Color(0xffffff)
                g2.fillRect(0, 0, width, height)

                val renderingContext = object : RenderingContext {
                    val verticalCoef = 0.6
                    override fun graphics(): Graphics2D {
                        return g2
                    }

                    override fun translateVertical(y: Int): Int {
                        return y
                    }

                    override fun translateHorizontal(x: Int): Int {
                        return x
                    }

                    override fun translatePoint(p: Point): Point {
                        val l = Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY())
                        val a = Math.atan2(p.getY(), p.getX())

                        val addAngle = (System.currentTimeMillis() % 30000) / 30000.0 * 2 * Math.PI

                        val x = Math.cos(a + addAngle) * l + width / 2
                        val y = Math.sin(a + addAngle) * l * verticalCoef + height / 2
                        return Point(x.toInt(), y.toInt())

                    }

                }

                g2.color = Color(0x999999)

                var x = -forestWidth / 2
                while (x < forestWidth / 2) {
                    x += 200
                    val p1 = renderingContext.translatePoint(Point(x, forestHeight / 2))
                    val p2 = renderingContext.translatePoint(Point(x, -forestHeight / 2))
                    g2.drawLine(p1.x, p1.y, p2.x, p2.y)
                }

                var y = -forestHeight / 2
                while (y < forestHeight / 2) {
                    y += 200
                    val p1 = renderingContext.translatePoint(Point(-forestWidth / 2, y))
                    val p2 = renderingContext.translatePoint(Point(forestWidth / 2, y))
                    g2.drawLine(p1.x, p1.y, p2.x, p2.y)
                }

                g2.color = Color(0x666666)

                val p1 = renderingContext.translatePoint(Point(-forestWidth / 2, -forestHeight / 2))
                val p2 = renderingContext.translatePoint(Point(forestWidth / 2, -forestHeight / 2))
                val p3 = renderingContext.translatePoint(Point(forestWidth / 2, forestHeight / 2))
                val p4 = renderingContext.translatePoint(Point(-forestWidth / 2, forestHeight / 2))

                g2.drawLine(p1.x, p1.y, p2.x, p2.y)
                g2.drawLine(p2.x, p2.y, p3.x, p3.y)
                g2.drawLine(p3.x, p3.y, p4.x, p4.y)
                g2.drawLine(p4.x, p4.y, p1.x, p1.y)



                models1.sortBy { it.model.getZIndex(renderingContext) }
                models1.forEach { it.renderBody(renderingContext, worldContext) }

            }

        }
        contentPane = jpanel
        isVisible = true


        while (true) {

            jpanel.repaint()

            models2.forEach { it.updateState(worldContext) }

            Thread.sleep(10)

        }

    }

    companion object {

        @JvmStatic fun main(args: Array<String>) {
            val dialog = Belochki()
        }
    }
}
