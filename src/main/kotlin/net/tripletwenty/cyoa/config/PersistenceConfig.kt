package net.tripletwenty.cyoa.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["net.tripletwenty.cyoa.core.repositories"])
class PersistenceConfig
