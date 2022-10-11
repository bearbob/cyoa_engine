package net.tripletwenty.coya.core.entities

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "events")
class Event(
    val label: String,
    val comment: String,
) : AuditedEntity()
