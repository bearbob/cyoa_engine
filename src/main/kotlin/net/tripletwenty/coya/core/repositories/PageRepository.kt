package net.tripletwenty.coya.core.repositories

import net.tripletwenty.coya.core.entities.Page
import org.springframework.data.repository.CrudRepository
import java.util.*

interface PageRepository: CrudRepository<Page, Long> {

    override fun findById(id: Long): Optional<Page>

    fun findByLabel(label: String): Page?

}