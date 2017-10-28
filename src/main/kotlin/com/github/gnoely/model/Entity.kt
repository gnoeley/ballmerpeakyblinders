package com.github.gnoely.model

import au.com.console.jpaspecificationdsl.*
import javax.persistence.*

@Entity
@Table(name="orders")
data class Order(
        @Id
        @Column(name="order_id")
        val id: String,
        val userId: String,
        val evalSet: String,
        val orderNumber: Int,
        val orderDayOfTheWeek: Int,
        val orderHourOfDay: Int,
        val daysSincePriorOrder: Int,
        @OneToMany
        @JoinTable(
                name = "order_products_train",
                joinColumns = arrayOf(JoinColumn(name = "order_id")),
                inverseJoinColumns = arrayOf(JoinColumn(name="product_id"))
        )
        val productsTrain: List<Product>,
        @OneToMany
        @JoinTable(
                name = "order_products_prior",
                joinColumns = arrayOf(JoinColumn(name = "order_id")),
                inverseJoinColumns = arrayOf(JoinColumn(name="product_id"))
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
//        @ManyToOne
//        val orders: List<Order>,
        val name: String,
        val aisleId: String,
        val departmentId: String
)
