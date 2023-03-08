package net.tripletwenty.cyoa.core.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "page_translations")
class PageTranslation(

    @Column(nullable = false)
    val locale: String,

    @Column(name = "raw_content")
    val content: String

) : AuditedEntity() {

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "page_label", referencedColumnName = "label")
    lateinit var page: Page
}
