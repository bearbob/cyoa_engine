package net.tripletwenty.coya.core.entities

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
    var lastActionAt: Instant = java.time.Instant.now(),
) : AuditedEntity()
