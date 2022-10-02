package net.tripletwenty.coya.utils

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.random.Random

class NumberConverterTest {

    @ParameterizedTest
    @CsvSource(
        "1,1,1,2Td",
        "751,7861,4588,142UdX88",
        "7994,8410,3412,dCY7Wbf6"
    )
    fun `Can convert key to string`(page: String, user: String, state: String, expectation: String) {
        // Given
        val key = KeyDto(page.toLong(), user.toLong(), state.toLong())
        // When
        val encoded = NumberConverter.encode(key)
        // Then
        assertThat(encoded, `is`(expectation))
    }

    @Test
    fun `Can convert string to key`() {
        // Given
        val value = "2Td"
        // When
        val decoded = NumberConverter.decode(value)
        // Then
        assertThat(decoded, `is`(KeyDto(1, 1, 1)))
    }

    @RepeatedTest(20)
    fun `Encoding and decoding gives the same result`() {
        // Given
        val key = KeyDto(
            Random.nextLong(0, 10000),
            Random.nextLong(0, 10000),
            Random.nextLong(0, 10000),
        )
        // When
        val encoded = NumberConverter.encode(key)
        val decoded = NumberConverter.decode(encoded)
        // Then
        assertThat(decoded, `is`(key))
    }

}