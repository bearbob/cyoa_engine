package net.tripletwenty.coya

import net.tripletwenty.coya.core.entities.Page
import net.tripletwenty.coya.core.repositories.PageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort

@SpringBootTest(classes = [CoyaApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class IntegrationTest {

    @LocalServerPort
    private val port = 0

    @Autowired
    lateinit var pageRepository: PageRepository

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
}
