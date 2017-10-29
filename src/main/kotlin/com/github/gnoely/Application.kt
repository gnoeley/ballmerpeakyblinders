package com.github.gnoely

import com.github.gnoely.service.OrderService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class Application {

    @Bean
    fun commandLineRunner(service: OrderService): CommandLineRunner {
        return CommandLineRunner {
            println("STARTED!")
            val start = System.nanoTime()
//            service.findAllProductsForUserId("36335").forEach { println(it) }
            val end = System.nanoTime()
            println("FINISHED!: ${end - start}ns")
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
