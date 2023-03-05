package net.tripletwenty.coya.core.repositories

import jakarta.transaction.Transactional
import net.tripletwenty.coya.core.entities.NavigationOption
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface NavigationOptionRepository : CrudRepository<NavigationOption, Long> {

    override fun findById(id: Long): Optional<NavigationOption>

    @Transactional
    @Modifying
    @Query(
        value ="""
            INSERT INTO navigation_option_source (
                option_label,
                source_page,
                created_at,
                created_by,
                modified_at
            ) VALUES (
                :optionLabel,
                :sourcePage,
                now(), 'jpa', now()
            )
        """,
        nativeQuery = true
    )
    fun insertSource(optionLabel: String, sourcePage: String)

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
