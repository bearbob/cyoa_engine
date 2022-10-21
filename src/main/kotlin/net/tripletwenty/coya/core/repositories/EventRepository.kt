package net.tripletwenty.coya.core.repositories

import net.tripletwenty.coya.core.entities.Event
import org.springframework.data.repository.CrudRepository

interface EventRepository : CrudRepository<Event, Long> {

    fun findByLabel(label: String): Event?
}
