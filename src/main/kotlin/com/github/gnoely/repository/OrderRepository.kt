package com.github.gnoely.repository

import com.github.gnoely.model.Order
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<Order, String>, JpaSpecificationExecutor<Order> {

    fun findAllByUserId(userId: String): List<Order>

}
