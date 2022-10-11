package net.tripletwenty.coya.core.entities

import com.fasterxml.jackson.databind.ObjectMapper
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "pages")
class Page(

    @Column(nullable = false)
    val label: String,

    val stateDelta: String? = null,

    @Column(name = "raw_content")
    val content: String,

) : AuditedEntity() {

    fun getStateDelta(): StateDelta {
        return this.stateDelta?.let {
            val mapper = ObjectMapper()
            return mapper.readValue(this.stateDelta, StateDelta::class.java)
        } ?: StateDelta()
    }

}

data class StateDelta(
    val items: List<ItemDelta>? = null,
    val events: List<String>? = null,
) {
    fun isEmpty(): Boolean {
        return items.isNullOrEmpty() && events.isNullOrEmpty()
    }
}

data class ItemDelta (
    val label: String,
    val change: Int,
)