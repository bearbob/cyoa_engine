package net.tripletwenty.coya.utils

import org.slf4j.LoggerFactory
import kotlin.math.pow

class NumberConverter {

    companion object {

        private val logger = LoggerFactory.getLogger(NumberConverter::class.java)

        private const val ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        private const val BASE = ALPHABET.length

        /**
         * Concatenate the numbers and add a number
         * in front that indicates how many literals the user number
         * has.
         * Format: <lengthPage><page><lengthUser><user><state>
         */
        fun encode(key: KeyDto): String {
            val user = key.user.toString()
            val page = key.page.toString()
            val state = key.state.toString()
            val startString: String = """
                ${page.length}$page${user.length}$user$state
            """.trimIndent()
            return compressString(startString)
        }

        internal fun compressString(input: String): String {
            var tempVal = input.toLong()
            var result = ""

            if (tempVal == 0L) return ALPHABET[0].toString()

            while (tempVal > 0) {
                val remaining = (tempVal % BASE).toInt()
                result += ALPHABET[remaining]
                tempVal /= net.tripletwenty.coya.utils.NumberConverter.Companion.BASE
            }

            return result.reversed()
        }

        fun decode(input: String?): KeyDto? {
            logger.error("Start decoding key '$input'")
            val value = input?.trim()
            if (value.isNullOrEmpty()) return null
            try {
                var baseTen: Long = 0
                value.reversed().toCharArray().forEachIndexed { index, c ->
                    baseTen += ALPHABET.indexOf(c) * BASE.toDouble().pow(index).toLong()
                }
                val convertedString = baseTen.toString()
                logger.info("Decoding key, converted string: $convertedString")

                val pageEnd = convertedString[0].digitToInt() + 1
                if (pageEnd > convertedString.length) {
                    logger.error("Error, parsed page end ($pageEnd) is longer than the actual string (${convertedString.length}")
                    return null
                }
                val page = convertedString.substring(1, pageEnd).let {
                    logger.info("Decoding key, page: $it")
                    it.toLong()
                }
                val userEnd = convertedString[pageEnd].digitToInt() + pageEnd + 1
                if (userEnd > convertedString.length) {
                    logger.error("Error, parsed user end ($userEnd) is longer than the actual string (${convertedString.length}")
                    return null
                }
                val user = convertedString.substring(pageEnd + 1, userEnd).let {
                    logger.info("Decoding key, user: $it")
                    it.toLong()
                }
                val state = convertedString.substring(userEnd).let {
                    logger.info("Decoding key, state: $it")
                    it.toLong()
                }

                return KeyDto(page, user, state)
            } catch (ex: Exception) {
                logger.error("Failed decoding key $value", ex)
                return null
            }
        }
    }
}

data class KeyDto(
    val page: Long,
    val user: Long,
    val state: Long,
)
