package com.github.gnoely.repository

import com.github.gnoely.model.Order
import com.github.gnoely.model.Product
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<Product, String>, JpaSpecificationExecutor<Product> {

    fun findAllByIsFoodDepartment(isFoodDepartment: Boolean): List<Product>
}
