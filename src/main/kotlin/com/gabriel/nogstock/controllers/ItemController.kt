package com.gabriel.nogstock.controllers

import com.gabriel.nogstock.entities.Item
import com.gabriel.nogstock.repositories.ItemRepository
import com.gabriel.nogstock.services.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/items")
class ItemController {

    @Autowired
    lateinit var itemService: ItemService

//    @Autowired
//    lateinit var itemRepository: ItemRepository

    @GetMapping("{companyId}")
    fun getByCompanyName(@PathVariable companyId: String): Flux<Item> = itemService.findByCompanyId(companyId)

    @PostMapping
    fun save(@RequestBody item: Item): Mono<Item> = itemService.save(item)

}