package net.tripletwenty.cyoa

import com.fasterxml.jackson.databind.ObjectMapper
import net.tripletwenty.cyoa.core.entities.NavigationOption
import net.tripletwenty.cyoa.core.entities.Page
import net.tripletwenty.cyoa.core.entities.PageStatus
import net.tripletwenty.cyoa.core.entities.State
import net.tripletwenty.cyoa.core.entities.StateEvent
import net.tripletwenty.cyoa.core.entities.StateItem

abstract class UnitTest {

    val mapper = ObjectMapper()

    fun getTestPage(
        label: String = "testPage",
        stateDelta: String? = null,
        content: String = "Page Content"
    ): Page {
        return Page(
            label,
            stateDelta,
            content,
            status = PageStatus.PUBLISHED
        ).apply {
            this.id = 22L
        }
    }

    fun getTestNavigationOption(
        label: String = "test",
        targetPage: String = "test",
        text: String = "test",
        conditions: String? = null
    ): NavigationOption {
        return NavigationOption(
            label = label,
            targetPage = targetPage,
            text = text,
            conditions = conditions
        )
    }

    fun getTestState(
        items: List<StateItem>? = null,
        events: List<StateEvent>? = null
    ): State {
        return State("", "").apply {
            this.items = items
            this.events = events
        }
    }
}
