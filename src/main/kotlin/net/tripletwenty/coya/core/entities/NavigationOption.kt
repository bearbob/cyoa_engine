package net.tripletwenty.coya.core.entities

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "navigation_options")
class NavigationOption(
    val sourcePage: String,
    val targetPage: String,
    val text: String,
    val conditions: String? = null,
) : AuditedEntity()
