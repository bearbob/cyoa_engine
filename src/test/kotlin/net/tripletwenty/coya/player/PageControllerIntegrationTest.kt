package net.tripletwenty.coya.player

import net.tripletwenty.coya.CoyaApplication
import net.tripletwenty.coya.IntegrationTest
import net.tripletwenty.coya.player.PageService.Companion.DEFAULT_LABEL
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


class PageControllerIntegrationTest: IntegrationTest() {

    val restTemplate = TestRestTemplate()
    val headers: HttpHeaders = HttpHeaders()

    @Test
    fun `Reading without a key opens start page`() {
        // Given
        val entity: HttpEntity<String> = HttpEntity(null, headers)
        val defaultPageContent = "Default_text_with_some_noise"
        createPage(
            content = defaultPageContent,
            label = DEFAULT_LABEL
        )
        // When
        val response: ResponseEntity<String> = restTemplate.exchange(
            createURLWithPort("/api/v1/page/read"),
            HttpMethod.GET, entity, String::class.java
        )
        // Then
        val expected = """{"body":"$defaultPageContent","options": []}""".trimMargin()

        JSONAssert.assertEquals(expected, response.body, false)

        /*
        mockMvc.perform(get("/api/v1/page/read").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.").value("test"))

         */
    }

}