package net.tripletwenty.coya.core.entities

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "events")
class Event(
    val label: String,
    val comment: String,
) : AuditedEntity()
