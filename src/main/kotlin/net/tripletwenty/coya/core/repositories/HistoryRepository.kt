package net.tripletwenty.coya.core.repositories

import net.tripletwenty.coya.core.entities.History
import org.springframework.data.repository.CrudRepository

interface HistoryRepository : CrudRepository<History, Long>
