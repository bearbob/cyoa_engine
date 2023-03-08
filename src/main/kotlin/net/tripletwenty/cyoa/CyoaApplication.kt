package net.tripletwenty.cyoa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class CyoaApplication

fun main(args: Array<String>) {
    runApplication<CyoaApplication>(*args)
}
