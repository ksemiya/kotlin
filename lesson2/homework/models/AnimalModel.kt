package kss.tasks.pdf2.belochki.models

import kss.tasks.pdf2.belochki.entities.TreePart
import kss.tasks.pdf2.belochki.entities.WorldContext
import kss.tasks.pdf2.belochki.entities.animals.Animal
import kss.tasks.pdf2.belochki.rendering.Model
import kss.tasks.pdf2.belochki.rendering.RenderingContext
import java.awt.Point
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class AnimalModel(val image: BufferedImage, animal: Animal) : Model<Animal>(animal) {

    override fun getZIndex(renderingContext: RenderingContext): Double {
        val rootPoint = renderingContext.translatePoint(Point(
                entity.tree.x,
                entity.tree.y
        ))
        return rootPoint.getY() + 0.1
    }

    override fun renderShadow(renderingContext: RenderingContext, worldContext: WorldContext) {

    }

    override fun renderBody(renderingContext: RenderingContext, worldContext: WorldContext) {

        renderingContext.graphics().apply {

            val rootPoint = renderingContext.translatePoint(Point(entity.tree.x, entity.tree.y))

            val p1x = rootPoint.x - renderingContext.translateHorizontal(20)
            val pw = renderingContext.translateHorizontal(40)

            val p1y = when (entity.treePart) {
                TreePart.roots -> rootPoint.y - renderingContext.translateVertical(40)
                TreePart.trunk -> rootPoint.y - renderingContext.translateVertical(80)
                TreePart.leafs -> rootPoint.y - renderingContext.translateVertical(120)
            }

            val ph = renderingContext.translateVertical(40)

            drawImage(image, p1x, p1y, pw, ph, null)


        }

    }

    companion object {

        val squirrel = ImageIO.read(File("src/main/resources/squirrel.png"))

    }

}