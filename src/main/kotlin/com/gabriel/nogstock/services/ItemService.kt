package com.gabriel.nogstock.services

import com.gabriel.nogstock.entities.Item
import com.gabriel.nogstock.repositories.ItemRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ItemService(
        val itemRepository: ItemRepository
) {


    fun save(item: Item): Mono<Item> = itemRepository.save(item)
    fun findByName(name: String): Mono<Item> = itemRepository.findByName(name)
    fun findDifference(item: Item): Int = item.currentQuantity - item.neededQuantity
    fun findByCompanyId(companyId: String): Flux<Item> = itemRepository.findByCompanyId(companyId)


}