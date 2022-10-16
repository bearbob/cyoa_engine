package net.tripletwenty.coya

import net.tripletwenty.coya.core.entities.NavigationOption
import net.tripletwenty.coya.core.entities.Page
import net.tripletwenty.coya.core.repositories.NavigationOptionRepository
import net.tripletwenty.coya.core.repositories.PageRepository
import net.tripletwenty.coya.core.repositories.StateRepository
import net.tripletwenty.coya.core.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.jdbc.Sql

@SpringBootTest(classes = [CoyaApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = ["file:src/test/resources/sql/clean_data.sql"])
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

    internal fun createURLWithPort(uri: String): String? {
        return "http://localhost:$port$uri"
    }

    internal fun createPage(
        content: String = "default_content",
        label: String = "default_label",
    ): Page {
        return pageRepository.save(
            Page(
                content = content,
                label = label
            )
        )
    }

    internal fun createOption(
        sourcePage: String,
        targetPage: String,
        text: String = "Option",
        conditions: String? = null,
    ): NavigationOption {
        return navigationOptionRepository.save(
            NavigationOption(
                sourcePage,
                targetPage,
                text,
                conditions,
            )
        )
    }
}
