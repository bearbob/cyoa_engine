package net.tripletwenty.coya.player

import net.tripletwenty.coya.core.entities.History
import net.tripletwenty.coya.core.entities.ItemChangeMode
import net.tripletwenty.coya.core.entities.ItemDelta
import net.tripletwenty.coya.core.entities.Page
import net.tripletwenty.coya.core.entities.State
import net.tripletwenty.coya.core.entities.StateDelta
import net.tripletwenty.coya.core.entities.StateEvent
import net.tripletwenty.coya.core.entities.StateItem
import net.tripletwenty.coya.core.entities.User
import net.tripletwenty.coya.core.repositories.HistoryRepository
import net.tripletwenty.coya.core.repositories.NavigationOptionRepository
import net.tripletwenty.coya.core.repositories.PageRepository
import net.tripletwenty.coya.core.repositories.StateEventRepository
import net.tripletwenty.coya.core.repositories.StateItemRepository
import net.tripletwenty.coya.core.repositories.StateRepository
import net.tripletwenty.coya.core.repositories.UserRepository
import net.tripletwenty.coya.player.dto.OptionDto
import net.tripletwenty.coya.player.dto.PageResponseDto
import net.tripletwenty.coya.utils.Hasher
import net.tripletwenty.coya.utils.KeyDto
import net.tripletwenty.coya.utils.NumberConverter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class PageService(
    val pageRepository: PageRepository,
    val navigationOptionRepository: NavigationOptionRepository,
    val userRepository: UserRepository,
    val stateRepository: StateRepository,
    val stateItemRepository: StateItemRepository,
    val stateEventRepository: StateEventRepository,
    val historyRepository: HistoryRepository,
) {

    companion object {
        const val DEFAULT_LABEL = "default"
    }

    private val logger = LoggerFactory.getLogger(javaClass)

    fun getPage(pageKey: String?): PageResponseDto {
        var key = NumberConverter.decode(pageKey)
        if (key == null) {
            val user = userRepository.save(User())
            key = KeyDto(0L, user.id!!, 0L)
        }

        var page = pageRepository.findById(key.page).orElse(null)

        var state = stateRepository.findById(key.state).orElse(null)
        if (page == null || state == null) {
            page = pageRepository.findByLabel(DEFAULT_LABEL)!!
            state = stateRepository.save(State())
        }

        val user = userRepository.findById(key.user).orElseGet {
            userRepository.save(User())
        }

        historyRepository.save(
            History(
                user.id!!,
                page.id!!,
                state.id!!
            )
        )

        val newState = updateState(state, page.getStateDelta())
        user.lastActionAt = Instant.now()
        userRepository.save(user)

        val options = getOptions(page, user.id, newState)
        return PageResponseDto(
            page.content,
            options
        )
    }

    fun getOptions(page: Page, userId: Long, stateId: Long): List<OptionDto> {
        val options = navigationOptionRepository.findBySourcePage(page.label)
        val state = stateRepository.findById(stateId).get()

        val result = mutableListOf<OptionDto>()
        options.forEach {
            if (!it.isAvailable(state)) return@forEach
            val target = pageRepository.findByLabel(it.targetPage)
                ?: return@forEach
            val pageKey = NumberConverter.encode(
                KeyDto(target.id!!, userId, stateId)
            )
            result.add(OptionDto(it.text, pageKey))
        }
        return result
    }

    fun updateState(state: State, delta: StateDelta): Long {
        if (delta.items.isNullOrEmpty() && delta.events.isNullOrEmpty()) return state.id!!

        val adaptedItems: MutableList<StateItem> = state.items?.toMutableList()
            ?: mutableListOf()
        delta.items?.forEach { itemDelta ->
            applyItemDelta(adaptedItems, itemDelta)
        }
        adaptedItems.sortBy { it.itemLabel }

        val adaptedEvents: MutableSet<String> = state.events?.map {
            it.eventLabel
        }?.toMutableSet()
            ?: mutableSetOf()
        if (delta.events != null) {
            adaptedEvents.addAll(delta.events)
        }
        val newItemHash = Hasher.hash(adaptedItems.joinToString { it.toString() })
        val newEventHash = Hasher.hash(adaptedEvents.sorted().joinToString { it })
        val matchingState = stateRepository.findByItemHashAndEventHash(newItemHash, newEventHash)
        if (matchingState != null) {
            return matchingState.id!!
        }
        val newState = stateRepository.save(
            State(newEventHash, newItemHash)
        )
        // update the lists with the new ID
        val stateEvents: List<StateEvent> = adaptedEvents.map {
            StateEvent(newState.id!!, it)
        }
        val stateItems: List<StateItem> = adaptedItems.map { StateItem(newState.id!!, it.itemLabel, it.amount) }
        stateItemRepository.saveAll(stateItems)
        stateEventRepository.saveAll(stateEvents)

        return newState.id!!
    }

    internal fun applyItemDelta(items: MutableList<StateItem>, delta: ItemDelta) {
        if (!delta.isValid()) {
            logger.error("Invalid item delta: $delta, skipping")
            return
        }
        items.firstOrNull { it.itemLabel == delta.label } ?.apply {
            when (delta.mode) {
                ItemChangeMode.ADD -> this.amount += delta.change
                ItemChangeMode.REMOVE -> run {
                    this.amount -= delta.change
                    if (this.amount <= 0) {
                        if (this.amount < 0) {
                            logger.error("New value of ${delta.label} is negative for state")
                        }
                        items.remove(this)
                    }
                }
                ItemChangeMode.SET -> if (delta.change != 0) {
                    this.amount = delta.change
                } else {
                    items.remove(this)
                }
            }
        } ?: when (delta.mode) {
            ItemChangeMode.ADD, ItemChangeMode.SET -> if (delta.change != 0) {
                items.add(StateItem(0L, delta.label, delta.change))
            }
            ItemChangeMode.REMOVE -> logger.error("Tried to remove from ${delta.label}, but not present for state ${state.id}")
        }
    }
}
