package com.github.gnoely

import com.github.gnoely.repository.AisleRepository
import com.github.gnoely.repository.OrderRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import javax.sql.DataSource


@SpringBootApplication
open class Application {

    @Bean
    open fun dataSource(): DataSource {
        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        val builder = EmbeddedDatabaseBuilder()
        return builder
                .setType(EmbeddedDatabaseType.H2)
                .setName("shopping")
                .addScript("db/schema.sql")
                .addScript("db/data.sql")
                .build()
    }

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