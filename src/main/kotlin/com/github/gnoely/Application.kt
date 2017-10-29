package com.github.gnoely

import com.github.gnoely.model.Product
import com.github.gnoely.service.OrderService
import com.github.gnoely.service.ProductService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.TimeUnit


@SpringBootApplication
class Application {

    @Bean
    fun commandLineRunner(service: OrderService, productService: ProductService): CommandLineRunner {
        return CommandLineRunner {
            println("STARTED!")
            val start = System.nanoTime()
//            service.findAllProductsForTwitterHandle("leonpelech").forEach { println(it) }
//            productService.findAllInFoodDepartments().forEach { println(it) }
            val end = System.nanoTime()
            println("FINISHED!: ${TimeUnit.NANOSECONDS.toMillis(end - start)}ms")


        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
