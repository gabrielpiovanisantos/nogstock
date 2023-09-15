package com.gabriel.nogstock.repositories

import com.gabriel.nogstock.entities.Company
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface CompanyRepository : ReactiveMongoRepository<Company, String> {

    fun findByName(name: String): Mono<Company>
    fun findByDocument(document: String): Mono<Company>

}