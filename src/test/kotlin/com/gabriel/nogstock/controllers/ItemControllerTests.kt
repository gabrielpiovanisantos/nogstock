package com.gabriel.nogstock.controllers

import com.gabriel.nogstock.entities.Item
import com.gabriel.nogstock.repositories.ItemRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux

@WebFluxTest
class ItemControllerTests {

//    @Autowired
//    lateinit var operations: ReactiveMongoOperations

    @MockBean
    lateinit var itemRepository: ItemRepository

    @Autowired
    lateinit var client: WebTestClient
    
    @Test
    fun `find all from a company`() {

        val companyId = "1"
        Mockito.`when`(itemRepository.findByCompanyId(companyId))
                .thenReturn(Flux.just(Item(1, 5, "rice", companyId = companyId),
                        Item(5, 10, "bean", companyId = companyId)))

        client.get()
                .uri("http://localhost:8080/items/{companyName}", companyId)
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("@.[0].name").isEqualTo("rice")
    }
}