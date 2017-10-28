package com.github.gnoely.model

import au.com.console.jpaspecificationdsl.*
import javax.persistence.*

@Entity
data class Order(
        @Id
        @Column(name="order_id")
        val id: String,
        val userId: String,
        val evalSet: Boolean,
        val orderNumber: Int,
        val orderDayOfTheWeek: Int,
        val orderHourOfDay: Int,
        val daysSincePriorOrder: Int,
        @OneToMany
        @JoinTable(
                name = "ORDER_PRODUCTS_TRAIN",
                joinColumns = arrayOf(JoinColumn(name = "ORDER_ID")),
                inverseJoinColumns = arrayOf(JoinColumn(name="PRODUCT_ID"))
        )
        val productsTrain: List<Product>,
        @JoinTable(
                name = "ORDER_PRODUCTS_PRIOR",
                joinColumns = arrayOf(JoinColumn(name = "ORDER_ID")),
                inverseJoinColumns = arrayOf(JoinColumn(name="PRODUCT_ID"))
        )
        val productsPrior: List<Product>
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
        @Column(name="product_id")
        val id: String,
        val name: String,
        val aisleId: String,
        val departmentId: String
)
