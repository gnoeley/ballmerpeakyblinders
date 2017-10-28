package com.github.gnoely.model

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
