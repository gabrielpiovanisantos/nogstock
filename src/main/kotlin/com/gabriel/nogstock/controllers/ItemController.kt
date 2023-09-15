package com.gabriel.nogstock.controllers

import com.gabriel.nogstock.entities.Item
import com.gabriel.nogstock.services.ItemService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/items")
class ItemController(
        val itemService: ItemService
) {

    @GetMapping(params = ["companyId"])
    fun getByCompanyId(@RequestParam companyId: String) = itemService.findByCompanyId(companyId)

    @GetMapping
    fun getAll() = itemService.findAll()

    @GetMapping(params = ["name"])
    fun findByName(@RequestParam name: String) = itemService.findByName(name)

    @GetMapping(params = ["id"])
    fun getDifference(@RequestParam id: String)  = itemService.findDifference(id)

    @GetMapping("{id}")
    fun getById(@PathVariable id: String)  = itemService.findById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody item: Item) = itemService.save(item)

}