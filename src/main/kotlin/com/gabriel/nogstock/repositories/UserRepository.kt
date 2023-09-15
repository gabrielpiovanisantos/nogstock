package com.gabriel.nogstock.repositories

import com.gabriel.nogstock.entities.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono


interface UserRepository : ReactiveMongoRepository<User, String> {

    fun findByLastName(lastName: String): Mono<User>
    fun findByLogin(login: String): Mono<User>
    fun findByDocument(document: String): Mono<User>

}