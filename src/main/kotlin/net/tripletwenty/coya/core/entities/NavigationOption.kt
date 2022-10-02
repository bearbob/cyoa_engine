package net.tripletwenty.coya.core.entities

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "navigation_options")
class NavigationOption(
    val sourcePage: String,
    val targetPage: String,
    val text: String,
    val conditions: String? = null,
) : AuditedEntity()
