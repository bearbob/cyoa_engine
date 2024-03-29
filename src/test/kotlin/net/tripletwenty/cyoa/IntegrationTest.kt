package net.tripletwenty.cyoa

import com.fasterxml.jackson.databind.ObjectMapper
import net.tripletwenty.cyoa.core.entities.Event
import net.tripletwenty.cyoa.core.entities.Item
import net.tripletwenty.cyoa.core.entities.NavigationOption
import net.tripletwenty.cyoa.core.entities.Page
import net.tripletwenty.cyoa.core.entities.PageStatus
import net.tripletwenty.cyoa.core.entities.State
import net.tripletwenty.cyoa.core.entities.StateDelta
import net.tripletwenty.cyoa.core.repositories.EventRepository
import net.tripletwenty.cyoa.core.repositories.ItemRepository
import net.tripletwenty.cyoa.core.repositories.NavigationOptionRepository
import net.tripletwenty.cyoa.core.repositories.PageRepository
import net.tripletwenty.cyoa.core.repositories.StateRepository
import net.tripletwenty.cyoa.core.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.jdbc.Sql

@SpringBootTest(classes = [CyoaApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = ["/sql/clean_data.sql"])
abstract class IntegrationTest {

    @LocalServerPort
    private val port = 0

    @Autowired
    lateinit var pageRepository: PageRepository

    @Autowired
    lateinit var navigationOptionRepository: NavigationOptionRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var stateRepository: StateRepository

    @Autowired
    lateinit var itemRepository: ItemRepository

    @Autowired
    lateinit var eventRepository: EventRepository

    val mapper = ObjectMapper()

    internal fun createURLWithPort(uri: String): String? {
        return "http://localhost:$port$uri"
    }

    internal fun createPage(
        content: String = "default_content",
        label: String = "default_label",
        delta: StateDelta? = null
    ): Page {
        return pageRepository.save(
            Page(
                content = content,
                label = label,
                stateDelta = if (delta == null) null else mapper.writeValueAsString(delta),
                status = PageStatus.PUBLISHED
            )
        )
    }

    internal fun createOption(
        sourcePage: String,
        targetPage: String,
        text: String = "Option",
        conditions: String? = null
    ): NavigationOption {
        val label = sourcePage + targetPage
        val navOption = navigationOptionRepository.save(
            NavigationOption(
                label,
                targetPage,
                text,
                conditions
            )
        )
        navigationOptionRepository.insertSource(label, sourcePage)
        return navOption
    }

    internal fun createItem(
        label: String = "item",
        comment: String = ""
    ): Item {
        return itemRepository.save(Item(label, comment))
    }

    internal fun createEvent(
        label: String = "event",
        comment: String = ""
    ): Event {
        return eventRepository.save(Event(label, comment))
    }

    internal fun createState() {
        stateRepository.save(State())
    }
}
