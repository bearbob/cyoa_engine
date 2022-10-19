package net.tripletwenty.coya.player

import net.tripletwenty.coya.IntegrationTest
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
        val defaultPageContent = "Default_text_with_some_noise"
        val secondPageLabel = "second_page"
        createPage(
            content = defaultPageContent,
            label = DEFAULT_LABEL
        )
        createPage(
            content = defaultPageContent.reversed(),
            label = secondPageLabel
        )
        createOption(DEFAULT_LABEL, secondPageLabel, "Shown")
        createOption(DEFAULT_LABEL, "whatever_label", "Hidden")
        // When
        val response: ResponseEntity<String> = restTemplate.exchange(
            createURLWithPort("/api/v1/page/read"),
            HttpMethod.GET, entity, String::class.java
        )
        // Then
        val expected = """
            {
                "body":"$defaultPageContent",
                "options": [
                    {"text": "Shown", "url": "39l"}
                ]
            }
        """.trimMargin()

        JSONAssert.assertEquals(expected, response.body, false)
    }
}
