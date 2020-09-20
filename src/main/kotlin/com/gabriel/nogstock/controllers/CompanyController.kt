package com.gabriel.nogstock.controllers

import com.gabriel.nogstock.entities.Company
import com.gabriel.nogstock.services.CompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/companies")
class CompanyController(
        val companyService: CompanyService
) {


    @GetMapping("")
    fun findByName(@RequestParam name: String): Mono<Company> = companyService.findByName(name)

    @GetMapping("{id}")
    fun findById(@PathVariable id: String): Mono<Company> = companyService.findById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody company: Company): Mono<Company> = companyService.save(company)
}