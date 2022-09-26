package net.tripletwenty.coya.player

import net.tripletwenty.coya.CoyaApplication
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity


@SpringBootTest(classes = [CoyaApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PageControllerIntegrationTest {

    @LocalServerPort
    private val port = 0

    val restTemplate = TestRestTemplate()
    val headers: HttpHeaders = HttpHeaders()

    @Test
    fun `Reading without a key opens start page`() {
        // Given
        val entity: HttpEntity<String> = HttpEntity(null, headers)
        // When
        val response: ResponseEntity<String> = restTemplate.exchange(
            createURLWithPort("/api/v1/page/read"),
            HttpMethod.GET, entity, String::class.java
        )
        // Then
        TODO("Adapt test")
        val expected = """{"id":"Course1","name":"Spring","description":"10 Steps"}"""

        JSONAssert.assertEquals(expected, response.body, false)
    }

    private fun createURLWithPort(uri: String): String? {
        return "http://localhost:$port$uri"
    }

}