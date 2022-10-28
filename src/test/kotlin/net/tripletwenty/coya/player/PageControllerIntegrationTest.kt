package net.tripletwenty.coya.player

import net.tripletwenty.coya.IntegrationTest
import net.tripletwenty.coya.core.entities.ItemChangeMode
import net.tripletwenty.coya.core.entities.ItemDelta
import net.tripletwenty.coya.core.entities.StateDelta
import net.tripletwenty.coya.player.PageService.Companion.DEFAULT_LABEL
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity

class PageControllerIntegrationTest : IntegrationTest() {

    val restTemplate = TestRestTemplate()
    val headers: HttpHeaders = HttpHeaders()

    @Test
    fun `Reading without a key opens start page`() {
        // Given
        val entity: HttpEntity<String> = HttpEntity(null, headers)
        createSampleContent()
        // When
        val response: ResponseEntity<String> = restTemplate.exchange(
            createURLWithPort("/api/v1/page/read"),
            HttpMethod.GET, entity, String::class.java
        )
        // Then
        val expected = """
            {
                "body":"Default_text_with_some_noise",
                "options": [
                    {"text": "Shown", "url": "39m"}
                ]
            }
        """.trimMargin()

        JSONAssert.assertEquals(expected, response.body, false)
    }

    @Test
    fun `Reading with a key opens the page with matching ID`() {
        // Given
        val entity: HttpEntity<String> = HttpEntity(null, headers)
        val params = mapOf(
            "key" to "39l"
        )
        createSampleContent()
        // When
        val response: ResponseEntity<String> = restTemplate.exchange(
            createURLWithPort("/api/v1/page/read?page={key}"),
            HttpMethod.GET, entity, String::class.java, params
        )
        // Then
        val expected = """
            {
                "body":"Second page content",
                "options": [
                    {"text": "Again", "url": "39m"},
                    {"text": "Next Page", "url": "3pu"}
                ]
            }
        """.trimMargin()

        JSONAssert.assertEquals(expected, response.body, false)
    }

    private fun createSampleContent() {
        val defaultPageContent = "Default_text_with_some_noise"
        val secondPageLabel = "second_page"
        createPage(
            content = "Default_text_with_some_noise",
            label = DEFAULT_LABEL
        )
        createOption(DEFAULT_LABEL, secondPageLabel, "Shown")
        createOption(DEFAULT_LABEL, "whatever_label", "Hidden")

        createItem("item")
        createEvent("event")
        createPage(
            content = "Second page content",
            label = secondPageLabel,
            delta = StateDelta(
                listOf(ItemDelta(
                    "item",
                    2,
                    ItemChangeMode.ADD
                )),
                listOf("event")
            )
        )
        createOption(secondPageLabel, secondPageLabel, "Again")
        createOption(secondPageLabel, "third_page", "Next Page")

        createPage(
            content = "Third page content",
            label = "third_page"
        )
        createState()
    }
}