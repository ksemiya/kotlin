package kss.tasks.pdf2.belochki.entities.trees

import kss.tasks.pdf2.belochki.models.TreeModel
import kss.tasks.pdf2.belochki.rendering.Model

class Birch(x: Int, y: Int) : Tree(
        x = x, y = y,
        trunkHoles = arrayListOf(),
        rootHoles = arrayListOf(),
        leafResources = arrayListOf(),
        trunkResources = arrayListOf(),
        rootResources = arrayListOf(),
        connectedTrees = ArrayList()
) {
    override val model: Model<*>
        get() = TreeModel(TreeModel.birch, 80, this)
}