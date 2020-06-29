package com.gabriel.nogstock.entities

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Company(
        val name: String,
        val address: Address,
        val document: String,
        var user: User,
        var itens : ArrayList<Item>,
        @CreatedDate val createdDate: LocalDateTime = LocalDateTime.now())
{
    companion object {
        @Id
        val Id: String? = null
    }
}