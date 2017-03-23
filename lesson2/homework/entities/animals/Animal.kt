package kotlin.lesson2.homework.entities.animals

import kss.tasks.pdf2.belochki.entities.GameEntity
import kss.tasks.pdf2.belochki.entities.MovingAbility
import kss.tasks.pdf2.belochki.entities.TreePart
import kss.tasks.pdf2.belochki.entities.WorldContext
import kss.tasks.pdf2.belochki.entities.resources.Resource
import kss.tasks.pdf2.belochki.entities.trees.Tree

abstract class Animal(

        var tree: Tree,
        var treePart: TreePart,
        val eatableResources: List<Class<Resource>>,
        val movingAbilities: List<MovingAbility>,
        val maxFullness: Double,
        val angerPerTime: Double

) : GameEntity() {

    override fun updateState(worldContext: WorldContext) {

    }

}