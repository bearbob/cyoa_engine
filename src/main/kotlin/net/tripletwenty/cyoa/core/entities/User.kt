package net.tripletwenty.cyoa.core.entities

import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "users")
class User(
    var lastActionAt: Instant = java.time.Instant.now()
) : AuditedEntity()
