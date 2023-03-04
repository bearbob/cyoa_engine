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
        getConditions().forEach {
            val varValue = state.items
            TODO()
        }
        return true
    }

}

data class Condition(
    val variable: String,
    val value: Int?,
    val comparator: Comparator
)

enum class Comparator {
    HAPPENED,
    NOT_HAPPENED,
    EQUAL,
    LESS,
    GREATER,
    LEQ,
    NEQ,
    GEQ;
}