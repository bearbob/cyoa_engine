package net.tripletwenty.coya.core.entities

import net.tripletwenty.coya.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class NavigationOptionUnitTest: UnitTest() {

    @Nested
    inner class AvailabilityTest {

        @Test
        fun `Is available when no conditions given` () {
            // Given
            val option = getTestNavigationOption(
                conditions = null
            )
            val state = getTestState()
            // When
            val result = option.isAvailable(state)
            // Then
            assertThat(result).isTrue
        }

        @Nested
        inner class ItemAmountTests {

            @Test
            fun `Is available when item has expected value` () {
                // Given
                val option = getTestNavigationOption(
                    conditions = "item == 4"
                )
                val state = getTestState(
                    items = listOf(StateItem(0L, "item", 4))
                )
                // When
                val result = option.isAvailable(state)
                // Then
                assertThat(result).isTrue
            }

            @Test
            fun `Is not available when item has different value` () {
                // Given
                val option = getTestNavigationOption(
                    conditions = "item == 5"
                )
                val state = getTestState(
                    items = listOf(StateItem(0L, "item", 4))
                )
                // When
                val result = option.isAvailable(state)
                // Then
                assertThat(result).isFalse
            }

        }

        @Test
        fun `Is available when event has happened` () {
            // Given
            val option = getTestNavigationOption(
                conditions = "event happened"
            )
            val state = getTestState(
                events = listOf(StateEvent(0L, "event"))
            )
            // When
            val result = option.isAvailable(state)
            // Then
            assertThat(result).isTrue
        }

        @Test
        fun `Is available when event is expected to not have happened` () {
            // Given
            val option = getTestNavigationOption(
                conditions = "b not happened"
            )
            val state = getTestState(
                events = listOf(StateEvent(0L, "event"))
            )
            // When
            val result = option.isAvailable(state)
            // Then
            assertThat(result).isTrue
        }

        @Test
        fun `Is not available when event should not have happened` () {
            // Given
            val option = getTestNavigationOption(
                conditions = "event not happened"
            )
            val state = getTestState(
                events = listOf(StateEvent(0L, "event"))
            )
            // When
            val result = option.isAvailable(state)
            // Then
            assertThat(result).isFalse
        }

        @Test
        fun `Is not available when event have not happened` () {
            // Given
            val option = getTestNavigationOption(
                conditions = "a happened && b happened"
            )
            val state = getTestState(
                events = listOf(StateEvent(0L, "event"))
            )
            // When
            val result = option.isAvailable(state)
            // Then
            assertThat(result).isFalse
        }

        @Test
        fun `Is not available when one event didn't happen` () {
            // Given
            val option = getTestNavigationOption(
                conditions = "event happened && b happened"
            )
            val state = getTestState(
                events = listOf(StateEvent(0L, "event"))
            )
            // When
            val result = option.isAvailable(state)
            // Then
            assertThat(result).isFalse
        }

    }

}