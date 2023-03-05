package net.tripletwenty.coya.utils

import net.tripletwenty.coya.UnitTest
import net.tripletwenty.coya.utils.NumberConverter.Companion.compressString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.random.Random

class NumberConverterTest : UnitTest() {

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
        assertThat(encoded).isEqualTo(expectation)
    }

    @Nested
    inner class DecodingTests {

        @Test
        fun `Can convert string to key`() {
            // Given
            val value = "2Td"
            // When
            val decoded = NumberConverter.decode(value)
            // Then
            assertThat(decoded).isEqualTo(KeyDto(1, 1, 1))
        }

        @Test
        fun `Return null if input is null`() {
            // Given
            val value = null
            // When
            val decoded = NumberConverter.decode(value)
            // Then
            assertThat(decoded).isNull()
        }

        @Test
        fun `Return null if input is empty`() {
            // Given
            val value = ""
            // When
            val decoded = NumberConverter.decode(value)
            // Then
            assertThat(decoded).isNull()
        }

        @Test
        fun `Return null if input is blank`() {
            // Given
            val value = "   "
            // When
            val decoded = NumberConverter.decode(value)
            // Then
            assertThat(decoded).isNull()
        }

        @Test
        fun `Return null if input with wrong page length`() {
            // Given
            val value = compressString("91110")
            // When
            val decoded = NumberConverter.decode(value)
            // Then
            assertThat(decoded).isNull()
        }

        @Test
        fun `Return null if input with wrong user length`() {
            // Given
            val value = compressString("11710")
            // When
            val decoded = NumberConverter.decode(value)
            // Then
            assertThat(decoded).isNull()
        }

        @Test
        fun `Return null if input is malformed`() {
            // Given
            val value = "11_10"
            // When
            val decoded = NumberConverter.decode(value)
            // Then
            assertThat(decoded).isNull()
        }
    }

    @RepeatedTest(20)
    fun `Encoding and decoding gives the same result`() {
        // Given
        val key = KeyDto(
            Random.nextLong(0, 10000),
            Random.nextLong(0, 10000),
            Random.nextLong(0, 10000)
        )
        // When
        val encoded = NumberConverter.encode(key)
        val decoded = NumberConverter.decode(encoded)
        // Then
        assertThat(decoded).isEqualTo(key)
    }
}
