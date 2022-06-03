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
         * Format: <length><user><page>
         */
        fun encode(key: KeyDto): String {
            val user = key.user.toString()
            val page = key.page.toString()
            val startString: String = user.length.toString() + user + page
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

        fun decode(value: String): KeyDto {
            var baseTen: Long = 0
            value.reversed().toCharArray().forEachIndexed { index, c ->
                baseTen += ALPHABET.indexOf(c) * BASE.toDouble().pow(index).toLong()
            }
            val resultString = baseTen.toString()
            val userLength = resultString.substring(0, 1).toInt()
            return KeyDto(
                resultString.substring(userLength + 1).toInt(),
                resultString.substring(1, userLength+1).toInt()
            )
        }

    }

}

data class KeyDto (val page: Int, val user: Int)