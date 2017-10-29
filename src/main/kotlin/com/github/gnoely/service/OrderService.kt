package com.github.gnoely.service

import com.github.gnoely.model.Order
import com.github.gnoely.model.Product
import com.github.gnoely.repository.OrderRepository
import com.github.gnoely.repository.TwitterUserRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class OrderService(val orderRepo: OrderRepository, val twitterUserRepo: TwitterUserRepository) {

    fun findAllByUserId(userId: String): List<Order> {
        return orderRepo.findAllByUserId(userId)
    }

    fun findAllProductsForUserId(userId: String): Set<Product> {
        val orders = orderRepo.findAllByUserId(userId)
        val productsPrior = orders.flatMap { it.productsPrior }
        val productsTrain = orders.flatMap { it.productsTrain }
        return productsPrior.union(productsTrain)
    }

    fun findAllProductsForTwitterHandle(handle: String): Set<Product> {
        val userId = twitterUserRepo.findOne(handle)?.userId
        return if (userId != null) findAllProductsForUserId(userId) else emptySet()
    }
}
