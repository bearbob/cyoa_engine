package net.tripletwenty.coya.admin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/admin/page")
class AdminPageController(
    private val pageService: AdminPageService
) {

    @GetMapping("/read")
    fun getPage(pageKey: String) = pageService.getPage(pageKey)

    @GetMapping("/load")
    fun loadDefaultPages() = pageService.loadDefaultPages()
}
