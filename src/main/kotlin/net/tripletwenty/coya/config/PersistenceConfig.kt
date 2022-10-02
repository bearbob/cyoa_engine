package net.tripletwenty.coya.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["net.tripletwenty.coya.core.repositories"])
class PersistenceConfig
