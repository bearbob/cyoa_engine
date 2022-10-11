package net.tripletwenty.coya.core.entities

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "items")
class Item(
    val label: String,
    val comment: String,
) : AuditedEntity()
