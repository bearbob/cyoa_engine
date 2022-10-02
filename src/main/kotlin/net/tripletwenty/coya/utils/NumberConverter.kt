package net.tripletwenty.coya.utils

import kotlin.math.pow

class NumberConverter {

    companion object {

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
            var tempVal = startString.toLong()
            var result = ""

            if (tempVal == 0L) return ALPHABET[0].toString()

            while (tempVal > 0) {
                val remaining = (tempVal % BASE).toInt()
                result += ALPHABET[remaining]
                tempVal /= BASE
            }

            return result.reversed()
        }

        fun decode(value: String?): KeyDto? {
            if (value.isNullOrEmpty()) return null
            try {
                var baseTen: Long = 0
                value.reversed().toCharArray().forEachIndexed { index, c ->
                    baseTen += ALPHABET.indexOf(c) * BASE.toDouble().pow(index).toLong()
                }
                val convertedString = baseTen.toString()

                val pageEnd = convertedString[0].digitToInt() + 1
                val page = convertedString.substring(1, pageEnd).toLong()
                val userEnd = convertedString[pageEnd].digitToInt() + pageEnd + 1
                val user = convertedString.substring(pageEnd+1, userEnd).toLong()
                val state = convertedString.substring(userEnd).toLong()

                return KeyDto(page, user, state)
            } catch (ex: Exception) {
                //TODO("Add logging for error")
                //return null
                throw ex
            }
        }

    }

}

data class KeyDto (
    val page: Long,
    val user: Long,
    val state: Long,
)