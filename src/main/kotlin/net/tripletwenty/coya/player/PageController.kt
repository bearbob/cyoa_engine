package net.tripletwenty.coya.player

import net.tripletwenty.coya.player.dto.PageResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/page")
class PageController(
    private val pageService: PageService
) {

    @GetMapping("/read")
    fun getPage(page: String?): PageResponseDto {
        return pageService.getPage(page)
    }
}
