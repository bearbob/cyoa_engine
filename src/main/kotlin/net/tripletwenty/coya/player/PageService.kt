package net.tripletwenty.coya.player

import net.tripletwenty.coya.core.entities.Page
import net.tripletwenty.coya.core.repositories.PageRepository
import net.tripletwenty.coya.player.dto.PageResponseDto
import net.tripletwenty.coya.utils.NumberConverter
import org.springframework.stereotype.Service

@Service
class PageService(
    val pageRepository: PageRepository
) {

    companion object {
        const val DEFAULT_LABEL = "default"
    }

    fun getPage(pageKey: String?): PageResponseDto {
        val page: Page = NumberConverter.decode(pageKey)
            ?.let {
                pageRepository.findById(it.page).orElse(
                    pageRepository.findByLabel(DEFAULT_LABEL)!!
                )
            }
            ?: pageRepository.findByLabel(DEFAULT_LABEL)!!

        // TODO fill dummy
        return PageResponseDto(
            page.content,
            emptyList()
        )
    }
}
