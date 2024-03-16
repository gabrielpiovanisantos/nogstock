package com.gabriel.nogstock.utils

import com.gabriel.nogstock.entities.Address
import com.gabriel.nogstock.entities.Company

class CompanyDTO(
    private val company: Company
    ) {
    val name: String = company.name
    val address: Address = company.address
    val document: String = company.document
    val userId: String = company.userId
}