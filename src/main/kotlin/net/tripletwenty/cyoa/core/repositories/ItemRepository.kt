package net.tripletwenty.cyoa.core.repositories

import net.tripletwenty.cyoa.core.entities.Item
import org.springframework.data.repository.CrudRepository

interface ItemRepository : CrudRepository<Item, Long> {

    fun findByLabel(label: String): Item?
}
