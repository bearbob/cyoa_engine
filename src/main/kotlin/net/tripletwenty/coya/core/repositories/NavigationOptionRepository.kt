package net.tripletwenty.coya.core.repositories

import net.tripletwenty.coya.core.entities.NavigationOption
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface NavigationOptionRepository : CrudRepository<NavigationOption, Long> {

    override fun findById(id: Long): Optional<NavigationOption>

    fun findBySourcePage(sourcePage: String): List<NavigationOption>

    fun findByTargetPage(targetPage: String): List<NavigationOption>
}
