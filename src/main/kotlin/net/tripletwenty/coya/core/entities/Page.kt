package net.tripletwenty.coya.core.entities

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.time.OffsetDateTime
import javax.persistence.*


@Entity
@Table(name = "pages")
class Page (

    @Column(nullable = false)
    val label: String,

    val state_delta: String? = null,

    @Column(name = "raw_content")
    val  content: String,

) :AuditedEntity()