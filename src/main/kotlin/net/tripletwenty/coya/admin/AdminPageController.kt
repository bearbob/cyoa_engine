package net.tripletwenty.coya.admin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value="api/v1/admin/page")
class AdminPageController (
    private val pageService: AdminPageService
){

    @GetMapping(value = "read")
    fun getPage(pageKey: String) = pageService.getPage(pageKey)

}