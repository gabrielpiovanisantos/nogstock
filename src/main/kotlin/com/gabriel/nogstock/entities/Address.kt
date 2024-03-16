package com.gabriel.nogstock.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Address(
        val cep: String,
        val street: String,
        val city: String,
        val state: String,
        val number: Int,
        var complement: String? = null,
        @Id val id: String? = null
)
