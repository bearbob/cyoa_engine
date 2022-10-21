package net.tripletwenty.coya.core.repositories

import net.tripletwenty.coya.core.entities.Item
import org.springframework.data.repository.CrudRepository

interface ItemRepository : CrudRepository<Item, Long> {

    fun findByLabel(label: String): Item?
}
