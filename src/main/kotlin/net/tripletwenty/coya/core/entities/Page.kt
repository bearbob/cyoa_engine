package net.tripletwenty.coya.core.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "pages")
class Page(

    @Column(nullable = false)
    val label: String,

    val state_delta: String? = null,

    @Column(name = "raw_content")
    val content: String,

) : AuditedEntity()
