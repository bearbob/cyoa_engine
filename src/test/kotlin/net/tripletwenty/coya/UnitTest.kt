package net.tripletwenty.coya

import com.fasterxml.jackson.databind.ObjectMapper
import net.tripletwenty.coya.core.entities.NavigationOption
import net.tripletwenty.coya.core.entities.State
import net.tripletwenty.coya.core.entities.StateEvent
import net.tripletwenty.coya.core.entities.StateItem

abstract class UnitTest {

    val mapper = ObjectMapper()

    fun getTestNavigationOption(
        label: String = "test",
        targetPage: String = "test",
        text: String = "test",
        conditions: String? = null,
    ): NavigationOption {
        return NavigationOption(
            label = label,
            targetPage = targetPage,
            text = text,
            conditions = conditions,
        )
    }

    fun getTestState(
        items: List<StateItem>? = null,
        events: List<StateEvent>? = null,
    ): State {
        return State("", "").apply {
            this.items = items
            this.events = events
        }
    }

}