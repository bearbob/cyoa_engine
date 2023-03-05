package net.tripletwenty.coya.core.entities

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "navigation_options")
class NavigationOption(
    val label: String,
    val targetPage: String,
    val text: String,
    private val conditions: String? = null,
) : AuditedEntity() {

    private fun getConditions(): List<Condition> {
        return this.conditions?.let {
            val mapper = ObjectMapper()
            return mapper.readValue(
                this.conditions,
                mapper.typeFactory.constructCollectionType(List::class.java, Condition::class.java)
            )
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
        return if ( condition.type == Condition.Type.EVENT) {
            state.events?.any { it.eventLabel == condition.variable } ?: false
        } else {
            val itemValue = state.items?.firstOrNull { it.itemLabel == condition.variable }?.amount ?: 0
            when (condition.comparator!!) {
                Condition.Comparator.EQUAL -> itemValue == condition.value!!
                Condition.Comparator.GEQ -> itemValue >= condition.value!!
                Condition.Comparator.GREATER -> itemValue > condition.value!!
                Condition.Comparator.LESS -> itemValue < condition.value!!
                Condition.Comparator.LEQ -> itemValue <= condition.value!!
                Condition.Comparator.NEQ -> itemValue != condition.value!!
            }
        }
    }
}

data class Condition(
    val variable: String,
    val value: Int?,
    val comparator: Comparator?,
    val type: Type,
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
        GEQ;
    }
}