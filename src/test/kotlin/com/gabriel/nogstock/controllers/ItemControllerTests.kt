package com.gabriel.nogstock.controllers
//
//import com.gabriel.nogstock.entities.Item
//import com.gabriel.nogstock.repositories.ItemRepository
//import org.junit.jupiter.api.Test
//import org.mockito.BDDMockito
//import org.mockito.Mockito
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
//import org.springframework.boot.test.mock.mockito.MockBean
//import org.springframework.http.MediaType
//import org.springframework.test.web.reactive.server.EntityExchangeResult
//import org.springframework.test.web.reactive.server.WebTestClient
//import reactor.core.publisher.Flux
//import reactor.core.publisher.Mono
//
//
//@WebFluxTest(ItemController::class)
//class ItemControllerTests {
//
////    @Autowired
////    lateinit var operations: ReactiveMongoOperations
//
//    @MockBean
//    lateinit var itemRepository: ItemRepository
//
//    @Autowired
//    lateinit var client: WebTestClient
//
//    @Test
//    fun `find all from a company`() {
//
//        val companyId = "1"
//        Mockito.`when`(itemRepository.findByCompanyId(companyId))
//                .thenReturn(Flux.just(Item(1, 5, "rice", companyId = companyId),
//                        Item(5, 10, "bean", companyId = companyId)))
//
//        client.get()
//                .uri("http://localhost:8080/items/{companyName}", companyId)
//                .exchange()
//                .expectStatus().isOk
//                .expectBody()
//                .jsonPath("@.[0].name").isEqualTo("rice")
//    }
//
//    @Test
//    fun `insert new item`() {
//        val item = Item(1, 5, "rice", companyId = "1")
//        BDDMockito.given(itemRepository.findByName("rice")).willReturn(Mono.just(item))
//
//
//        this.client.post().uri("/items/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .body(Mono.just<Any>(item), Item::class.java)
//                .exchange()
//                .expectBody()
//                .consumeWith { x: EntityExchangeResult<ByteArray?>? -> println(x) }
//
//    }
//}