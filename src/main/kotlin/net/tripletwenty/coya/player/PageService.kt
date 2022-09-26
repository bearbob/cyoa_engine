package net.tripletwenty.coya.player

import net.tripletwenty.coya.utils.NumberConverter
import org.springframework.stereotype.Service

@Service
class PageService {

    fun getPage(pageKey: String?): String {
        if (pageKey == null) {
            TODO ("Return start page")
        }
        val key = NumberConverter.decode(pageKey)
        // TODO fill dummy
        return "OK"
    }

}