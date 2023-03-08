package net.tripletwenty.cyoa.core.entities

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "items")
class Item(
    val label: String,
    val comment: String
) : AuditedEntity()
