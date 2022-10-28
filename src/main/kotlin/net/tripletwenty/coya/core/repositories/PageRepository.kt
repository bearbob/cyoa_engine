package net.tripletwenty.coya.core.repositories

import net.tripletwenty.coya.core.entities.Page
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface PageRepository : CrudRepository<Page, Long> {

    @Query(
        value = "SELECT * FROM pages p WHERE p.id = 1? AND p.status = 'PUBLISHED'",
        nativeQuery = true)
    override fun findById(id: Long): Optional<Page>

    @Query(
        value = "SELECT * FROM pages p WHERE p.label = 1? AND p.status = 'PUBLISHED'",
        nativeQuery = true)
    fun findByLabel(label: String): Page?
}
