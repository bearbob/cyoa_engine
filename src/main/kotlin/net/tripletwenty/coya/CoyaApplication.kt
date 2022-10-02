package net.tripletwenty.coya

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class CoyaApplication

fun main(args: Array<String>) {
	runApplication<CoyaApplication>(*args)
}
