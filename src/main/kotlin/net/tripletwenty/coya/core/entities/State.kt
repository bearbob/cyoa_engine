package net.tripletwenty.coya.core.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "states")
class State(
    val eventHash: String? = null,
    val itemHash: String? = null,
) : AuditedEntity() {

    @OneToMany
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    var items: List<StateItem>? = null

    @OneToMany
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    var events: List<StateEvent>? = null
}

@Entity
@Table(name = "state_items")
class StateItem(
    @Column(name = "state_id")
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
    @Column(name = "state_id")
    val stateId: Long,
    val eventLabel: String,
) : AuditedEntity()
