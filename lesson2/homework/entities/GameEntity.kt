package kotlin.lesson2.homework.entities

import kss.tasks.pdf2.belochki.rendering.Model
import kss.tasks.pdf2.belochki.rendering.RenderingContext

abstract class GameEntity {

    abstract fun updateState(worldContext: WorldContext)

    abstract val model: Model<*>

    open fun renderBody(renderingContext: RenderingContext, worldContext: WorldContext) {
        model.renderBody(renderingContext, worldContext)
    }

}
