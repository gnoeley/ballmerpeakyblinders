package com.github.gnoely

import com.github.gnoely.repository.AisleRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
open class Application {

    @Bean
    open fun commandLineRunner(repo: AisleRepository): CommandLineRunner {
        return CommandLineRunner {
            println(repo.findOne("1"))
            println("STARTED!")
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}