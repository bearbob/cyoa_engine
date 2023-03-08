package net.tripletwenty.cyoa.core.entities

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "navigation_options")
class NavigationOption(
    val label: String,
    val targetPage: String,
    val text: String,
    val conditions: String? = null
) : AuditedEntity() {

    private fun getConditions(): List<Condition> {
        return this.conditions?.let {
            it.lowercase().split("&&").map { c ->
                val conditionParts = c.trim().split(" ")
                if (c.contains(" happened")) {
                    Condition(
                        variable = conditionParts[0],
                        type = Condition.Type.EVENT,
                        comparator = if (c.contains(" not ")) {
                            Condition.Comparator.NEQ
                        } else {
                            Condition.Comparator.EQUAL
                        }
                    )
                } else {
                    Condition(
                        variable = conditionParts[0],
                        type = Condition.Type.ITEM,
                        comparator = when (conditionParts[1]) {
                            "=", "==" -> Condition.Comparator.EQUAL
                            "!=", "not" -> Condition.Comparator.NEQ
                            "<" -> Condition.Comparator.LESS
                            "<=" -> Condition.Comparator.LEQ
                            ">" -> Condition.Comparator.GREATER
                            ">=" -> Condition.Comparator.GREQ
                            else -> Condition.Comparator.EQUAL
                        },
                        value = conditionParts[2].toInt()
                    )
                }
            }
        } ?: emptyList()
    }

    fun isAvailable(state: State): Boolean {
        getConditions().forEach { c ->
            if (!isConditionSatisfied(c, state)) {
                return false
            }
        }
        return true
    }

    private fun isConditionSatisfied(condition: Condition, state: State): Boolean {
        return if (condition.type == Condition.Type.EVENT) {
            if (condition.comparator == Condition.Comparator.EQUAL) {
                state.events?.any { it.eventLabel == condition.variable } ?: false
            } else {
                state.events?.none { it.eventLabel == condition.variable } ?: true
            }
        } else {
            val itemValue = state.items?.firstOrNull { it.itemLabel == condition.variable }?.amount ?: 0
            when (condition.comparator) {
                Condition.Comparator.EQUAL -> itemValue == condition.value
                Condition.Comparator.GREQ -> itemValue >= condition.value
                Condition.Comparator.GREATER -> itemValue > condition.value
                Condition.Comparator.LESS -> itemValue < condition.value
                Condition.Comparator.LEQ -> itemValue <= condition.value
                Condition.Comparator.NEQ -> itemValue != condition.value
            }
        }
    }
}

data class Condition(
    val variable: String,
    val value: Int = -1,
    val comparator: Comparator,
    val type: Type
) {
    enum class Type {
        EVENT,
        ITEM
    }

    enum class Comparator {
        EQUAL,
        LESS,
        GREATER,
        LEQ,
        NEQ,
        GREQ;
    }
}
