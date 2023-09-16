package com.gabriel.nogstock.entities

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Company(
        val name: String,
        val address: Address,
        @Indexed(unique = true)
        val document: String,
        var userId: String,
        @CreatedDate val createdDate: LocalDateTime = LocalDateTime.now(),
        @Id val id: String? = null
)