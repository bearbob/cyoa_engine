package net.tripletwenty.cyoa.core.repositories

import net.tripletwenty.cyoa.core.entities.History
import org.springframework.data.repository.CrudRepository

interface HistoryRepository : CrudRepository<History, Long>
