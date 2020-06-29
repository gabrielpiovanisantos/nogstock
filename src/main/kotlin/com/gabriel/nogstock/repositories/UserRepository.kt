package com.gabriel.nogstock.repositories

import com.gabriel.nogstock.entities.Item
import com.gabriel.nogstock.entities.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface UserRepository : ReactiveCrudRepository<User, String> {

    fun findByName(name: String): Mono<Item>

}