package com.gabriel.nogstock.entities

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class User(
        val firstName: String,
        val lastName: String,
        var address: Address,
        var login: String,
        var password: String,
        @CreatedDate val createdDate: LocalDateTime = LocalDateTime.now())
{
    companion object {
        @Id
        val Id: String? = null
    }
}