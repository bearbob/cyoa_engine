package net.tripletwenty.coya.player

import io.mockk.every
import io.mockk.mockk
import net.tripletwenty.coya.UnitTest
import net.tripletwenty.coya.core.entities.Page
import net.tripletwenty.coya.core.entities.PageStatus
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

class PageServiceUnitTest : UnitTest() {

    private val pageRepository: PageRepository = mockk()
    private val navigationOptionRepository: NavigationOptionRepository = mockk()
    private val userRepository: UserRepository = mockk()
    private val stateRepository: StateRepository = mockk()
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
            val page = Page(
                "test",
                null,
                "test",
                PageStatus.PUBLISHED
            )
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
        fun `Returns list of all options available for page`() {
            TODO()
        }

        @Test
        fun `Option contains key of target page`() {
            TODO()
        }

        @Test
        fun `Don't list option where condition is not met`() {
            TODO()
        }

        @Test
        fun `List option where condition is met`() {
            TODO()
        }
    }
}
