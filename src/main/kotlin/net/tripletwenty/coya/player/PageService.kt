package net.tripletwenty.coya.player

import net.tripletwenty.coya.utils.NumberConverter

class PageService {

    fun getPage(pageKey: String): String {
        val key = NumberConverter.decode(pageKey)
        // TODO fill dummy
        return "OK"
    }



}