package net.tripletwenty.coya.player

import io.mockk.every
import io.mockk.mockk
import net.tripletwenty.coya.UnitTest
import net.tripletwenty.coya.core.entities.NavigationOption
import net.tripletwenty.coya.core.entities.State
import net.tripletwenty.coya.core.repositories.HistoryRepository
import net.tripletwenty.coya.core.repositories.NavigationOptionRepository
import net.tripletwenty.coya.core.repositories.PageRepository
import net.tripletwenty.coya.core.repositories.StateEventRepository
import net.tripletwenty.coya.core.repositories.StateItemRepository
import net.tripletwenty.coya.core.repositories.StateRepository
import net.tripletwenty.coya.core.repositories.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.Optional

class PageServiceUnitTest : UnitTest() {

    private val pageRepository: PageRepository = mockk()
    private val navigationOptionRepository: NavigationOptionRepository = mockk()
    private val userRepository: UserRepository = mockk()
    private val stateRepository: StateRepository = mockk {
        every { findById(any()) } returns Optional.of(State("", ""))
    }
    private val stateItemRepository: StateItemRepository = mockk()
    private val stateEventRepository: StateEventRepository = mockk()
    private val historyRepository: HistoryRepository = mockk()

    private val pageService = PageService(
        pageRepository = pageRepository,
        navigationOptionRepository = navigationOptionRepository,
        userRepository = userRepository,
        stateRepository = stateRepository,
        stateItemRepository = stateItemRepository,
        stateEventRepository = stateEventRepository,
        historyRepository = historyRepository
    )

    @Nested
    inner class FetchOptionsTests {

        @Test
        fun `Returns empty list if no options available`() {
            // Given
            val page = getTestPage("test")
            every { navigationOptionRepository.findBySourcePage(page.label) }
                .returns(emptyList())
            // When
            val result = pageService.getOptions(
                page,
                1L,
                1L
            )
            // Then
            assertThat(result).isEmpty()
        }

        @Test
        fun `Don't return options where the target does not exist`() {
            // Given
            val page = getTestPage("test")
            every { navigationOptionRepository.findBySourcePage(page.label) }
                .returns(
                    listOf(
                        NavigationOption("a", "target", "text", null)
                    )
                )
            every { pageRepository.findByLabel(any()) } returns null
            // When
            val result = pageService.getOptions(
                page,
                1L,
                1L
            )
            // Then
            assertThat(result).isEmpty()
        }

        @Test
        fun `Return option where the target exists`() {
            // Given
            val page = getTestPage("test")
            every { navigationOptionRepository.findBySourcePage(page.label) }
                .returns(
                    listOf(
                        NavigationOption("a", "target", "text", null)
                    )
                )
            every { pageRepository.findByLabel(any()) } returns getTestPage()
            // When
            val result = pageService.getOptions(
                page,
                1L,
                1L
            )
            // Then
            assertThat(result).isNotEmpty
            assertThat(result.size).isEqualTo(1)
        }

        @Test
        fun `Don't list option where condition is not met`() {
            // Given
            val page = getTestPage("test")
            every { navigationOptionRepository.findBySourcePage(page.label) }
                .returns(
                    listOf(
                        NavigationOption("a", "target", "text", "testvar > 2")
                    )
                )
            every { pageRepository.findByLabel(any()) } returns getTestPage()
            // When
            val result = pageService.getOptions(
                page,
                1L,
                1L
            )
            // Then
            assertThat(result).isEmpty()
        }

        @Test
        fun `List option where condition is met`() {
            // Given
            val page = getTestPage("test")
            every { navigationOptionRepository.findBySourcePage(page.label) }
                .returns(
                    listOf(
                        NavigationOption("a", "target", "text", "testvar < 2")
                    )
                )
            every { pageRepository.findByLabel(any()) } returns getTestPage()
            // When
            val result = pageService.getOptions(
                page,
                1L,
                1L
            )
            // Then
            assertThat(result).isNotEmpty
            assertThat(result.size).isEqualTo(1)
        }
    }
}
