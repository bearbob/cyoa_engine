package net.tripletwenty.coya.core.entities

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "states")
class State(
    val eventHash: String? = null,
    val itemHash: String? = null,
) : AuditedEntity()

@Entity
@Table(name = "state_items")
class StateItem(
    val stateId: Long,
    val itemId: Long,
    val amount: Int,
) : AuditedEntity()

@Entity
@Table(name = "state_events")
class StateEvent(
    val stateId: Long,
    val eventId: Long,
) : AuditedEntity()
