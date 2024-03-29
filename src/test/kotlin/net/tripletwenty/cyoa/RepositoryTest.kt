package net.tripletwenty.cyoa

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.jdbc.Sql

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(
    properties = [
        "spring.datasource.url=jdbc:postgresql://localhost:5432/postgres"
    ]
)
// https://www.arhohuttunen.com/spring-boot-datajpatest/
@Sql(scripts = ["/sql/clean_data.sql", "/sql/sample_content.sql"])
abstract class RepositoryTest
