package net.tripletwenty.cyoa.core.repositories

import net.tripletwenty.cyoa.core.entities.User
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface UserRepository : CrudRepository<User, Long> {

    override fun findById(id: Long): Optional<User>
}
