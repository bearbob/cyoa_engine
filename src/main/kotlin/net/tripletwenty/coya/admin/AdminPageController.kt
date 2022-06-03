package net.tripletwenty.coya.admin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("admin/page")
class AdminPageController {

    @Autowired
    private lateinit var pageService: AdminPageService

    @GetMapping("read")
    fun getPage(pageKey: String) = pageService.getPage(pageKey)

}