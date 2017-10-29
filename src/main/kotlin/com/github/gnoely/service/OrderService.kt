package com.github.gnoely.service

import com.github.gnoely.model.Order
import com.github.gnoely.model.Product
import com.github.gnoely.repository.OrderRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class OrderService(val repo: OrderRepository) {

    fun findAllByUserId(userId: String): List<Order> {
        return repo.findAllByUserId(userId)
    }

    fun findAllProductsForUserId(userId: String): Set<Product> {
        val orders = repo.findAllByUserId(userId)
        val productsPrior = orders.flatMap { it.productsPrior }
        val productsTrain = orders.flatMap { it.productsTrain }
        return productsPrior.union(productsTrain)
    }
}
