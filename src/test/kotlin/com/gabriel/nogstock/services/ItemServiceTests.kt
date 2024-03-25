package com.gabriel.nogstock.services

import com.gabriel.nogstock.entities.Item
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.test.StepVerifier

@SpringBootTest
class ItemServiceTests {

    @Autowired
    lateinit var itemService: ItemService
    @BeforeAll
    fun setUp() {
        val item = Item(5, 10, "rice", companyId = null, id = "1")
        itemService.save(item).then().block()

    }
    @AfterAll
    fun tearDown() {
        itemService.itemRepository.deleteAll().then().block()
    }
    @Test
    fun `find difference between quantities of an item`() {
        StepVerifier.create(itemService.findDifference("1"))
                .consumeNextWith {
                    run {
                        Assertions.assertThat(it == 5)
                    }
                }.verifyComplete()
    }
}