package com.github.gnoely.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Aisle(
        @Id
        @Column(name="aisle_id")
        val id: String = "",
        val aisle: String = ""
)

data class Department(val departmentId: Int, val department: String)
