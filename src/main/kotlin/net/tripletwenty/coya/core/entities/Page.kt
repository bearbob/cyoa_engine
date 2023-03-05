package net.tripletwenty.coya.core.entities

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table

@Entity
@Table(name = "pages")
class Page(

    @Column(nullable = false)
    val label: String,

    val stateDelta: String? = null,

    @Column(name = "raw_content")
    val content: String,

    @Enumerated(EnumType.STRING)
    val status: PageStatus

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
    val events: List<String>? = null
)

data class ItemDelta(
    @JsonProperty("label")
    val label: String,
    @JsonProperty("change")
    val change: Int,
    @JsonProperty("mode")
    val mode: ItemChangeMode
) {
    fun isValid(): Boolean {
        if (change < 0) return false
        if (mode != ItemChangeMode.SET && change <= 0) return false
        return true
    }

    override fun toString(): String {
        return "[$label,${mode.name},$change]"
    }
}

enum class ItemChangeMode {
    ADD,
    REMOVE,
    SET
}

enum class PageStatus {
    DRAFT,
    PUBLISHED,
    DELETED
}
