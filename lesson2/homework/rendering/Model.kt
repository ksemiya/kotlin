package kss.tasks.pdf2.belochki.rendering

import kss.tasks.pdf2.belochki.entities.GameEntity
import kss.tasks.pdf2.belochki.entities.WorldContext

abstract class Model<out T : GameEntity>(val entity: T) {

    abstract fun renderShadow(renderingContext: RenderingContext, worldContext: WorldContext)

    abstract fun renderBody(renderingContext: RenderingContext, worldContext: WorldContext)

    abstract fun getZIndex(renderingContext: RenderingContext) : Double

}