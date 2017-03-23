package kotlin.lesson2.homework.entities.trees

import kss.tasks.pdf2.belochki.entities.GameEntity
import kss.tasks.pdf2.belochki.entities.Hole
import kss.tasks.pdf2.belochki.entities.WorldContext
import kss.tasks.pdf2.belochki.entities.resources.Resource
import kss.tasks.pdf2.belochki.rendering.RenderingContext

abstract class Tree(
        val x: Int, val y: Int,
        val trunkHoles: ArrayList<Hole>,
        val rootHoles: ArrayList<Hole>,
        val leafResources: ArrayList<Resource>,
        val trunkResources: ArrayList<Resource>,
        val rootResources: ArrayList<Resource>,
        val connectedTrees: ArrayList<Tree>
) : GameEntity() {

    override fun updateState(worldContext: WorldContext) {
        leafResources.forEach { it.updateState(worldContext) }
        trunkResources.forEach { it.updateState(worldContext) }
        rootResources.forEach { it.updateState(worldContext) }
    }

    override fun renderBody(renderingContext: RenderingContext, worldContext: WorldContext) {
        super.renderBody(renderingContext, worldContext)
        leafResources.forEach { it.renderBody(renderingContext, worldContext) }
        trunkResources.forEach { it.renderBody(renderingContext, worldContext) }
        rootResources.forEach { it.renderBody(renderingContext, worldContext) }
    }

}