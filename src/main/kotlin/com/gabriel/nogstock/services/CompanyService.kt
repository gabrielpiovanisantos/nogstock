package com.gabriel.nogstock.services

import com.gabriel.nogstock.entities.Company
import com.gabriel.nogstock.repositories.CompanyRepository
import com.gabriel.nogstock.utils.CompanyDTO
import com.gabriel.nogstock.utils.CompanyNotFoundException
import com.gabriel.nogstock.utils.DocumentExistsException
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

private const val ALREADY_A_COMPANY_WITH_THE_DOCUMENT = "There is already a company with the document"

@Service
class CompanyService(
    val repository: CompanyRepository
) {
    fun save(company: Company): Mono<Company> =
        repository.findByDocument(company.document).flatMap<Company> {
            Mono.error<Company>(DocumentExistsException("$ALREADY_A_COMPANY_WITH_THE_DOCUMENT${company.document}"))
        }.switchIfEmpty(
            repository.save(company)
        )

    fun findByName(name: String): Mono<CompanyDTO> = repository.findByName(name).map { company -> CompanyDTO(company) }
        .switchIfEmpty { throw CompanyNotFoundException(name) }

    fun findById(id: String): Mono<CompanyDTO> = repository.findById(id).map { company -> CompanyDTO(company) }
        .switchIfEmpty { throw CompanyNotFoundException(id) }

    fun findAll(): Flux<CompanyDTO> = repository.findAll().map { company -> CompanyDTO(company) }


}
