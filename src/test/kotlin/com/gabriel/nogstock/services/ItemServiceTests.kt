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
        val item = Item(5, 10, "arroz", companyId = null)
        itemService.save(item).then().block()

    }

    @AfterAll
    fun tearDown() {
        itemService.itemRepository.deleteAll().then().block()
    }

    @Test
    fun `find difference between quantities`() {
        StepVerifier.create(itemService.findByName("arroz"))
                .consumeNextWith {
                    run {
                        Assertions.assertThat(itemService.findDifference(it)).isEqualTo(-5)
                    }
                }.verifyComplete()
    }

}