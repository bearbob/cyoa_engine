package net.tripletwenty.coya.core.repositories

import net.tripletwenty.coya.RepositoryTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class NavigationOptionRepositoryIntegrationTest: RepositoryTest() {

    @Autowired
    private lateinit var navigationOptionRepository: NavigationOptionRepository

    @Test
    fun `Return empty list if no option is linked to the given page` () {
        // Given
        val sourcePage = "gold_lost"
        // When
        val results = navigationOptionRepository.findBySourcePage(sourcePage)
        // Then
        assertThat(results).isEmpty()
    }

    @Test
    fun `If page has options linked, these are returned` () {
        // Given
        val sourcePage = "default"
        // When
        val results = navigationOptionRepository.findBySourcePage(sourcePage)
        // Then
        assertThat(results).isNotEmpty
    }

}