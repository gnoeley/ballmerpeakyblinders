package com.github.gnoely.model

import org.springframework.boot.SpringApplication

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

data class Aisle(val aisleId: Int, val asile: String)

data class Department(val departmentId: Int, val department: String)

@SpringBootApplication
open class Application {

    @Bean
    open fun commandLineRunner(repo: OrderRepository): CommandLineRunner {
        return CommandLineRunner {
            println("STARTED!")
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)

//        val start: Long = System.nanoTime()
//        val asilesFiles: List<Aisle> = Files.readAllLines(Paths.get("./data/raw/aisles.csv"))
//                .parallelStream()
//                .skip(1)
//                .map({
//                    val csv: List<String> = it.split(',')
//                    Aisle(csv[0].toInt(), csv[1])
//                })
////                .limit(10)
//                .collect(Collectors.toList())
//
//        val end: Long = System.nanoTime()
//        println("DONE: ${TimeUnit.NANOSECONDS.toMillis(end-start)}ms")
}
