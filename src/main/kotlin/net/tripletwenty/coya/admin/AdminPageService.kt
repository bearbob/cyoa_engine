package net.tripletwenty.coya.admin

import net.tripletwenty.coya.admin.dto.AdminPageDto
import net.tripletwenty.coya.admin.dto.OptionDto
import org.springframework.stereotype.Service

@Service
class AdminPageService {

    fun getPage(pageKey: String): AdminPageDto {
        TODO("Not implemented yet")
    }

    fun updatePage(page: AdminPageDto): AdminPageDto {
        TODO("Not implemented yet")
    }

    fun createPage(page: AdminPageDto): AdminPageDto {
        TODO("Not implemented yet")
    }

    fun addNavigationOptionToPage(pageId: String, option: OptionDto): AdminPageDto {
        TODO("Not implemented yet")
    }

}