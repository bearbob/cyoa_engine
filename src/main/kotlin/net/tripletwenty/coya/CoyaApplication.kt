package net.tripletwenty.coya

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoyaApplication

fun main(args: Array<String>) {
	runApplication<CoyaApplication>(*args)
}
