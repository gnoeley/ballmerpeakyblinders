package com.github.gnoely.model

import com.github.gnoely.model.filereader.FileReader
import java.util.concurrent.TimeUnit

data class Aisle(val aisleId: Int, val asile: String)

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
        val start: Long = System.nanoTime()

        val aisleStrings: List<String> = FileReader.readFile("./data/raw/aisles.csv");
        val aisles: List<Aisle> = aisleStrings.map {
            val splitString: List<String> = it.split(',');
            Aisle(splitString[0].toInt(), splitString[1])
        }
        aisles.forEach{ println(it)}

//        val asilesFiles: List<Aisle> = Files.readAllLines(Paths.get("./data/raw/aisles.csv"))
//                .parallelStream()
//                .skip(1)
//                .map({
//                    val csv: List<String> = it.split(',')
//                    Aisle(csv[0].toInt(), csv[1])
//                })
////                .limit(10)
//                .collect(Collectors.toList())

        val end: Long = System.nanoTime()
        println("DONE: ${TimeUnit.NANOSECONDS.toMillis(end-start)}ms")
    }
}
