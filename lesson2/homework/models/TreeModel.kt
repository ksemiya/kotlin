package kotlin.lesson2.homework.models

import kss.tasks.pdf2.belochki.entities.WorldContext
import kss.tasks.pdf2.belochki.entities.trees.Tree
import kss.tasks.pdf2.belochki.rendering.Model
import kss.tasks.pdf2.belochki.rendering.RenderingContext
import java.awt.Point
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class TreeModel(val image: BufferedImage, val width: Int, tree: Tree) : Model<Tree>(tree) {

    override fun getZIndex(renderingContext: RenderingContext): Double {
        val rootPoint = renderingContext.translatePoint(Point(entity.x, entity.y))
        return rootPoint.getY()
    }

    override fun renderShadow(renderingContext: RenderingContext, worldContext: WorldContext) {

    }

    override fun renderBody(renderingContext: RenderingContext, worldContext: WorldContext) {

        renderingContext.graphics().apply {

            val rootPoint = renderingContext.translatePoint(Point(entity.x, entity.y))

            val p1x = rootPoint.x - renderingContext.translateHorizontal(width / 2)
            val pw = renderingContext.translateHorizontal(width)

            val p1y = rootPoint.y - renderingContext.translateVertical(treeHeight)
            val ph = renderingContext.translateVertical(treeHeight)

            drawImage(image, p1x, p1y, pw, ph, null)


        }

    }

    companion object {

        val treeHeight = 150

        val birch = ImageIO.read(File("src/main/resources/birch.png"))
        val spruce = ImageIO.read(File("src/main/resources/spruce.png"))
        val pine = ImageIO.read(File("src/main/resources/pine.png"))
        val oak = ImageIO.read(File("src/main/resources/oak.png"))
        val maple = ImageIO.read(File("src/main/resources/maple.png"))

    }

}