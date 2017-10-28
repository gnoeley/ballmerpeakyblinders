package com.github.gnoely.model

import au.com.console.jpaspecificationdsl.*
import javax.persistence.*

@Entity
data class Order(
        @Id
        val id: String,
        val userId: String,
        val evalSet: Boolean,
        val orderNumber: Int,
        val orderDayOfTheWeek: Int,
        val orderHourOfDay: Int,
        val daysSincePriorOrder: Int,
        @OneToMany
        @JoinTable(
                name = "ORDER_PRODUCTS",
                joinColumns = arrayOf(JoinColumn(name = "ORDER_ID")),
                inverseJoinColumns = arrayOf(JoinColumn(name="PRODUCT_ID"))
        )
        val products: List<Product>
)

//@Entity
//data class OrderProducts(
//        val orderId: String,
//        val productId: String,
//        val addToCartOrder: Int,
//        val reordered: Boolean
//)

@Entity
data class Product(
        @Id
        val id: String,
        val name: String,
        val aisleId: String,
        val departmentId: String
)
