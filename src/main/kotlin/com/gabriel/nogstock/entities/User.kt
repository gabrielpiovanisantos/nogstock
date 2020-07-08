package com.gabriel.nogstock.entities

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class User(
        val firstName: String,
        val lastName: String,
        val document: String,
        var address: Address,
        var login: String,
        var password: String,
        @CreatedDate val createdDate: LocalDateTime = LocalDateTime.now(),
        @Id val id: String? = null
)