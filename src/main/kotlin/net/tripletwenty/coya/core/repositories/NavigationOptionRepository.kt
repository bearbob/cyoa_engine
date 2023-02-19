package net.tripletwenty.coya.core.repositories

import net.tripletwenty.coya.core.entities.NavigationOption
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface NavigationOptionRepository : CrudRepository<NavigationOption, Long> {

    override fun findById(id: Long): Optional<NavigationOption>

    @Query(
        value = """
            SELECT nav.* 
            FROM navigation_options nav
            JOIN navigation_option_source source
            ON nav.label = source.option_label
            WHERE source.source_page = ?1
        """,
        nativeQuery = true
    )
    fun findBySourcePage(sourcePage: String): List<NavigationOption>
}
