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
class ItemRepositoryTests (
        @Autowired
        var itemRepository: ItemRepository
) {


    @BeforeAll
    fun setUp() {
        itemRepository.save(Item(1, 5, "arroz")).
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
                        print(it.toString())
                    }
                }.verifyComplete()

    }
}