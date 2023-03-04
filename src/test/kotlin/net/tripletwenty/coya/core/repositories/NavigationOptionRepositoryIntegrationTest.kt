package net.tripletwenty.coya.core.repositories

import net.tripletwenty.coya.RepositoryTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired

class NavigationOptionRepositoryIntegrationTest: RepositoryTest() {

    @Autowired
    private lateinit var navigationOptionRepository: NavigationOptionRepository

    @Test
    fun `Return empty list if no option is linked to the given page` () {
        // Given
        val sourcePage = "orphan_page"
        // When
        val results = navigationOptionRepository.findBySourcePage(sourcePage)
        // Then
        assertThat(results).isEmpty()
    }

    @ParameterizedTest
    @CsvSource(
        "default,1",
        "crossroads,3"
    )
    fun `If page has options linked, these are returned` (sourcePage: String, expectedResults: Int) {
        // Given
        // When
        val results = navigationOptionRepository.findBySourcePage(sourcePage)
        // Then
        assertThat(results).isNotEmpty
        assertThat(results.size).isEqualTo(expectedResults)
    }

}