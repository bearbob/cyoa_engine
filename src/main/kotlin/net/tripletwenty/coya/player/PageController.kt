package net.tripletwenty.coya.player

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value="api/v1/page")
class PageController (
    private val pageService: PageService
) {

    @GetMapping("read")
    fun getPage(page: String) = pageService.getPage(page)

}