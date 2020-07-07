package com.gabriel.nogstock.controllers

import com.gabriel.nogstock.entities.Item
import com.gabriel.nogstock.services.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/items")
class ItemController {

    @Autowired
    lateinit var itemService: ItemService

    @GetMapping("{companyId}")
    fun getByCompanyName( @PathVariable companyId: String): Flux<Item> = itemService.findByCompanyId(companyId)
}