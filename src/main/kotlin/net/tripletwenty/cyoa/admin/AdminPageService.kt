package net.tripletwenty.cyoa.admin

import net.tripletwenty.cyoa.admin.dto.AdminPageDto
import net.tripletwenty.cyoa.admin.dto.OptionDto
import org.springframework.stereotype.Service

@Service
class AdminPageService {

    fun getPage(pageKey: String): AdminPageDto {
        TODO("Not implemented yet")
    }

    fun loadDefaultPages(): Boolean {
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
