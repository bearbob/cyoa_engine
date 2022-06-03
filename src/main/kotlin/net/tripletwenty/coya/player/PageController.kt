package net.tripletwenty.coya.player

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("page")
class PageController {

    @Autowired
    private lateinit var pageService: PageService

    @GetMapping("read")
    fun getPage(page: String) = pageService.getPage(page)

}