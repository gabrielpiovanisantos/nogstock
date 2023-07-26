package com.gabriel.nogstock.services

import com.gabriel.nogstock.entities.Company
import com.gabriel.nogstock.repositories.CompanyRepository
import com.gabriel.nogstock.utils.DocumentExistsException
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CompanyService(
        val companyRepository: CompanyRepository
) {

    fun save(company: Company): Mono<Company> {
        //TODO try to avoid the block() method because its breaks the reactiveness

        return companyRepository.findByDocument(company.document).flatMap { companyTmp ->
            if (companyTmp == null) {
                companyRepository.save(company)
            } else {
                Mono.error(DocumentExistsException("similar doc"))
            }
        }
    }

    fun findByName(name: String): Mono<Company> = companyRepository.findByName(name)
    fun findById(id: String): Mono<Company> = companyRepository.findById(id)
    fun findAll(): Flux<Company> = companyRepository.findAll()

}
