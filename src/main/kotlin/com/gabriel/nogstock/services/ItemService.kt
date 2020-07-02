package com.gabriel.nogstock.services

import com.gabriel.nogstock.entities.Item
import com.gabriel.nogstock.repositories.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import kotlin.math.abs

@Service
class ItemService {

    @Autowired
    lateinit var itemRepository: ItemRepository

    fun save(item: Item) : Mono<Item> = itemRepository.save(item)
    fun findByName(name: String) : Mono<Item> = itemRepository.findByName(name)
    fun findDifference(item: Item): Int = item.currentQuantity - item.neededQuantity


}