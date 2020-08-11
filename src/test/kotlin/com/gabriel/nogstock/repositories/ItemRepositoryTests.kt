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
    private val companyId: String = "1"
    @BeforeAll
    fun setUp() {
        val items = listOf(Item(1, 5, "rice", companyId = companyId),
                Item(5, 10, "bean", companyId = companyId))
        itemRepository.saveAll(items).then().block()
    }

    @AfterAll
    fun tearDown() {
        itemRepository.deleteAll().then().block()
    }

    @Test
    fun `find by name`() {
        StepVerifier.create(itemRepository.findByName("rice"))
                .consumeNextWith {
                    run {
                        assertThat(it.name).isEqualTo("rice")
                    }
                }.verifyComplete()

    }

    @Test
    fun `find by company id`() {
        val items = itemRepository.findByCompanyId(companyId)
        for (item in items.toIterable()) {
            println(item.toString())
        }
    }


}