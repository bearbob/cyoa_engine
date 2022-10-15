package net.tripletwenty.coya.core.entities

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "states")
class State(
    val eventHash: String? = null,
    val itemHash: String? = null,
) : AuditedEntity() {

    @OneToMany
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    val items: List<StateItem>? = null

    @OneToMany
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    val events: List<StateEvent>? = null
}

@Entity
@Table(name = "state_items")
class StateItem(
    val stateId: Long,
    val itemLabel: String,
    var amount: Int,
) : AuditedEntity() {
    override fun toString(): String {
        return "[$itemLabel;$amount]"
    }
}

@Entity
@Table(name = "state_events")
class StateEvent(
    val stateId: Long,
    val eventLabel: String,
) : AuditedEntity()
