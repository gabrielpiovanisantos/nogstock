package com.gabriel.nogstock.repositories

import com.gabriel.nogstock.entities.Item
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface ItemRepository : ReactiveMongoRepository<Item, String> {

    fun findByName(name: String): Flux<Item>
    fun findByCompanyId(companyId: String): Flux<Item>

}