package com.github.gnoely.repository

import com.github.gnoely.model.Order
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
interface OrderRepository : CrudRepository<Order, String>,
    JpaSpecificationExecutor<Order>,
    OrderRepositoryCustom {

    fun findAllByUserId(userId: String): List<Order>

}


interface OrderRepositoryCustom {
    fun findAllUniqueUserIds(): Set<String>
}

class OrderRepositoryImpl : OrderRepositoryCustom {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    override fun findAllUniqueUserIds(): Set<String> {
        return entityManager.createNativeQuery("SELECT DISTINCT user_id FROM orders")
            .resultList
            .map { it.toString() }
            .toHashSet()
    }
}
