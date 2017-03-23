package kotlin.lesson2.homework.entities.resources

import kss.tasks.pdf2.belochki.entities.GameEntity
import kss.tasks.pdf2.belochki.entities.WorldContext

abstract class Resource(val maxValue: Double, val restorePerTime: Double) : GameEntity() {

    override fun updateState(worldContext: WorldContext) {
        restore(restorePerTime)
    }

    private var value = maxValue

    fun isNotEmpty() = value >= 0

    fun use(amount: Double): Double {
        if (value >= amount) {
            value -= amount
            return amount
        } else {
            val oldValue = value
            value = 0.0
            return oldValue
        }
    }

    fun restore(amount: Double) {
        value = Math.max(value + amount, maxValue)
    }

}
