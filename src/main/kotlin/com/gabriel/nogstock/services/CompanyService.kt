package com.gabriel.nogstock.services

import com.gabriel.nogstock.entities.Company
import com.gabriel.nogstock.repositories.CompanyRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CompanyService(
        val companyRepository: CompanyRepository

) {


    fun save(company: Company): Mono<Company> {
        //TODO try to avoid the block() method because its breaks the reactiveness
//        val verifyCompanyDocument = companyRepository.findByDocument(company.document)
//        val blockedCompanyDocument = verifyCompanyDocument.block()
//        if (blockedCompanyDocument != null && blockedCompanyDocument.document == company.document) throw Exception("the document must be unique")
        return companyRepository.save(company)
    }

    fun findByName(name: String): Mono<Company> = companyRepository.findByName(name)
    fun findById(id: String): Mono<Company> = companyRepository.findById(id)

}
