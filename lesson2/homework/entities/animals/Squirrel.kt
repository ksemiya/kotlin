package kss.tasks.pdf2.belochki.entities.animals

import kss.tasks.pdf2.belochki.entities.TreePart
import kss.tasks.pdf2.belochki.entities.trees.Tree
import kss.tasks.pdf2.belochki.models.AnimalModel
import kss.tasks.pdf2.belochki.rendering.Model

class Squirrel(tree: Tree, treePart: TreePart) : Animal(
        tree = tree,
        treePart = treePart,
        eatableResources = arrayListOf(),
        movingAbilities = arrayListOf(),
        maxFullness = 25.0,
        angerPerTime = 0.01
) {

    override val model: Model<*>
        get() = AnimalModel(AnimalModel.squirrel, this)

}