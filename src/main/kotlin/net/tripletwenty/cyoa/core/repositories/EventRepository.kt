package net.tripletwenty.cyoa.core.repositories

import net.tripletwenty.cyoa.core.entities.Event
import org.springframework.data.repository.CrudRepository

interface EventRepository : CrudRepository<Event, Long> {

    fun findByLabel(label: String): Event?
}
