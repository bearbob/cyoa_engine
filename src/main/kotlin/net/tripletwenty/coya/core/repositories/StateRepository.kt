package net.tripletwenty.coya.core.repositories

import net.tripletwenty.coya.core.entities.State
import net.tripletwenty.coya.core.entities.StateEvent
import net.tripletwenty.coya.core.entities.StateItem
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface StateRepository : CrudRepository<State, Long> {

    override fun findById(id: Long): Optional<State>

    fun findByItemHashAndEventHash(itemHash: String, eventHash: String): State?
}

interface StateItemRepository : CrudRepository<StateItem, Long>

interface StateEventRepository : CrudRepository<StateEvent, Long>
