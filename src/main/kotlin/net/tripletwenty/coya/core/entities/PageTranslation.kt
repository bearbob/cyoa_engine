package net.tripletwenty.coya.core.entities

import javax.persistence.*

@Entity
@Table(name = "page_translations")
class PageTranslation(

    @Column(nullable = false)
    val label: String,

    @Column(nullable = false)
    val locale: String,

    @Column(name = "raw_content")
    val content: String,

) : AuditedEntity() {

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "page_id", referencedColumnName = "id")
    lateinit var page: Page

}
