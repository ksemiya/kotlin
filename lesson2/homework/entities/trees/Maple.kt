package kss.tasks.pdf2.belochki.entities.trees

import kss.tasks.pdf2.belochki.entities.Hole
import kss.tasks.pdf2.belochki.models.TreeModel
import kss.tasks.pdf2.belochki.rendering.Model

class Maple(x: Int, y: Int) : Tree(
        x = x, y = y,
        trunkHoles = arrayListOf(Hole(), Hole()),
        rootHoles = arrayListOf(Hole(), Hole()),
        leafResources = arrayListOf(),
        trunkResources = arrayListOf(),
        rootResources = arrayListOf(),
        connectedTrees = ArrayList()
) {


    override val model: Model<*>
        get() = TreeModel(TreeModel.maple, 90, this)

}