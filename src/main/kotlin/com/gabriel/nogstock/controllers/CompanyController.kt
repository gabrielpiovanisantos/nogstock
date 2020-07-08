package com.gabriel.nogstock.controllers

import com.gabriel.nogstock.entities.Company
import com.gabriel.nogstock.services.CompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/companies")
class CompanyController {

    @Autowired
    lateinit var companyService: CompanyService

    @GetMapping("{name}")
    fun findByName(@PathVariable name: String): Mono<Company> = companyService.findByName(name)

    @PostMapping
    fun save(@RequestBody company: Company): Mono<Company> = companyService.save(company)
}