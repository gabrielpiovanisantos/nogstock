package com.gabriel.nogstock.controllers

import com.gabriel.nogstock.entities.Item
import com.gabriel.nogstock.repositories.ItemRepository
import com.gabriel.nogstock.services.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/items")
class ItemController(
        val itemService: ItemService
) {

//    @Autowired

//    @Autowired
//    lateinit var repository: ItemRepository

    @GetMapping("{companyId}")
    fun getByCompanyId(@PathVariable companyId: String): Flux<Item> = itemService.findByCompanyId(companyId)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody item: Item): Mono<Item> = itemService.save(item)

}