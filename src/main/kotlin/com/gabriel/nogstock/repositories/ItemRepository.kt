package com.gabriel.nogstock.repositories

import com.gabriel.nogstock.entities.Item
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ItemRepository : ReactiveCrudRepository<Item, String> {

    fun findByName(name: String): Mono<Item>
    fun findByCompanyId(companyId: String): Flux<Item>

}