package net.tripletwenty.coya.utils

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import kotlin.random.Random

class NumberConverterTest {

    @Test
    fun `Can convert key to string`() {
        // Given
        val key = KeyDto(99, 3)
        // When
        val encoded = NumberConverter.encode(key)
        // Then
        assertThat(encoded, `is`("mz"))
    }

    @Test
    fun `Can convert string to key`() {
        // Given
        val value = "mz"
        // When
        val decoded = NumberConverter.decode(value)
        // Then
        assertThat(decoded, `is`(KeyDto(99, 3)))
    }

    @RepeatedTest(20)
    fun `Encoding and decoding gives the same result`() {
        // Given
        val key = KeyDto(
            Random.nextInt(0, 10000),
            Random.nextInt(0, 10000)
        )
        // When
        val encoded = NumberConverter.encode(key)
        val decoded = NumberConverter.decode(encoded)
        // Then
        assertThat(decoded, `is`(key))
    }

}