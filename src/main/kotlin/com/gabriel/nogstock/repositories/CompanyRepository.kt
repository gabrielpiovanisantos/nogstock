package com.gabriel.nogstock.repositories

import com.gabriel.nogstock.entities.Company
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface CompanyRepository : ReactiveCrudRepository<Company, String> {

    fun findByName(name: String): Mono<Company>
    fun findByDocument(document: String): Mono<Company>

}