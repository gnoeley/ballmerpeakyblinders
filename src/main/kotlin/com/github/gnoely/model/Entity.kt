package com.github.gnoely.model

import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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

@Repository
interface OrderRepository : CrudRepository<Order, Int>, JpaSpecificationExecutor<Order>

@RestController
class HelloController {

    @GetMapping("/hello")
    fun sayHello(): String {
        return java.lang.String.valueOf("hello")
    }

}