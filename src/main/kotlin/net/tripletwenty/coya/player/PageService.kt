package net.tripletwenty.coya.player

import net.tripletwenty.coya.core.entities.Page
import net.tripletwenty.coya.core.entities.StateDelta
import net.tripletwenty.coya.core.entities.User
import net.tripletwenty.coya.core.repositories.NavigationOptionRepository
import net.tripletwenty.coya.core.repositories.PageRepository
import net.tripletwenty.coya.core.repositories.UserRepository
import net.tripletwenty.coya.player.dto.OptionDto
import net.tripletwenty.coya.player.dto.PageResponseDto
import net.tripletwenty.coya.utils.KeyDto
import net.tripletwenty.coya.utils.NumberConverter
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class PageService(
    val pageRepository: PageRepository,
    val navigationOptionRepository: NavigationOptionRepository,
    val userRepository: UserRepository,
) {

    companion object {
        const val DEFAULT_LABEL = "default"
    }

    fun getPage(pageKey: String?): PageResponseDto {
        val key = NumberConverter.decode(pageKey)
        val page: Page = key?.let {
            pageRepository.findById(it.page).orElse(
                pageRepository.findByLabel(DEFAULT_LABEL)!!
            )
        }
            ?: pageRepository.findByLabel(DEFAULT_LABEL)!!
        val options = getOptions(page, key)
        return PageResponseDto(
            page.content,
            options
        )
    }

    fun getOptions(page: Page, key: KeyDto?): List<OptionDto> {
        val options = navigationOptionRepository.findBySourcePage(page.label)
        val updatedUserState: Pair<Long, Long> = if (page.label == DEFAULT_LABEL || key == null) {
            // reset state, create new user
            val user = userRepository.save(User())
            Pair(user.id!!, 0L)
        } else {
            // apply state change to key
            val user = userRepository.findById(key.user).orElseGet {
                userRepository.save(User())
            }
            user.lastActionAt = Instant.now()
            userRepository.save(user)
            val newState = updateState(key.state, page.getStateDelta())
            Pair(user.id!!, newState)
        }
        val result = mutableListOf<OptionDto>()
        options.forEach {
            val target = pageRepository.findByLabel(it.targetPage)
                ?: return@forEach
            val pageKey = NumberConverter.encode(
                KeyDto(target.id!!, updatedUserState.first, updatedUserState.second)
            )
        }
        return result
    }

    fun updateState(state: Long, delta: StateDelta): Long {
        if (delta.isEmpty()) return state
        TODO()
        // TODO get state entity
        // check what changed in delta - only items/events or both?
        // generate new hash for changed entities, store new entities
        // return id of new state
    }
}
