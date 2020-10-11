package com.gabriel.nogstock.controllers

import com.gabriel.nogstock.entities.Company
import com.gabriel.nogstock.services.CompanyService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/companies")
class CompanyController(
        val companyService: CompanyService
) {


    @GetMapping(params = ["name"])
    fun findByName(@RequestParam name: String) = companyService.findByName(name)

    @GetMapping
    fun findAll() = companyService.findAll()


    @GetMapping("{id}")
    fun findById(@PathVariable id: String) = companyService.findById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody company: Company) = companyService.save(company)
}