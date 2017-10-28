package com.github.gnoely.model

import java.nio.file.Files
import java.nio.file.Paths

data class Aisle(val aisleId: String, val asile: String)

data class Department(val departmentId: Int, val department: String)

data class OrderProducts(val orderId: Int, val productId: String, val addToCartOrder: Int, val reordered: Boolean)

data class Order(val orderId: Int,
                 val userId: String,
                 val evalSet: Boolean,
                 val orderNumber: Int,
                 val orderDayOfTheWeek: Int,
                 val orderHourOfDay: Int,
                 val daysSincePriorOrder: Int)

object Main {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        Files.readAllLines(Paths.get("./data/raw/aisles.csv"))
                .parallelStream()
                .skip(1)
                .map({
                    val csv: List<String> = it.split(',')
                    Aisle(csv[0], csv[1])
                })
                .limit(10)
                .forEach({ println(it) })
    }
}
