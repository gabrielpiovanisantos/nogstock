package com.gabriel.nogstock.repositories

import com.gabriel.nogstock.entities.Item
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import reactor.test.StepVerifier

@DataMongoTest
class ItemRepositoryTests {

    @Autowired
    lateinit var itemRepository: ItemRepository

    val companyId = "1"

    @BeforeAll
    fun setUp() {
        var itens = listOf<Item>(Item(1, 5, "arroz", companyId = companyId),
                Item(5, 10, "feijao", companyId = companyId))
        itemRepository.saveAll(itens).
        then().
        block()
    }

    @AfterAll
    fun tearDown() {
        itemRepository.deleteAll().then().block()
    }

    @Test
    fun `find by name`() {
        StepVerifier.create(itemRepository.findByName("arroz"))
                .consumeNextWith {
                    run {
                        assertThat(it.name).isEqualTo("arroz")
                    }
                }.verifyComplete()

    }

    @Test
    fun `find by company id`() {
        val aux = itemRepository.findByCompanyId(companyId)
        for(item in aux.toIterable()) {
            println(item.toString())
        }
    }


}