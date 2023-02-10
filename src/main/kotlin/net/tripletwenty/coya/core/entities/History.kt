package net.tripletwenty.coya.core.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.Hibernate
import org.springframework.data.annotation.CreatedDate
import java.time.Instant

@Entity
@Table(name = "history")
class History(
    val userId: Long,
    val pageId: Long,
    val stateId: Long,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @CreatedDate
    val createdAt: Instant = Instant.now()

    // https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as AuditedEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 1756406093
}
