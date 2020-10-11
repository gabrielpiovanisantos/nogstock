package com.gabriel.nogstock.services

import com.gabriel.nogstock.entities.Address
import com.gabriel.nogstock.entities.Company
import com.gabriel.nogstock.utils.DocumentExistsException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Mono
import reactor.kotlin.test.test

@SpringBootTest
class CompanyServiceTests {

    @Autowired
    lateinit var companyService: CompanyService

    @Autowired
    lateinit var userService: UserService

    @BeforeAll
    fun setUp() {
        val address = Address("18081260", "gentil", "sorocaba", "sp", 121)
        val company = Company("test", address, "210381280", "1")
        companyService.save(company).then().block()
    }

    @AfterAll
    fun tearDown() {
        companyService.companyRepository.deleteAll().then().block()
        userService.userRepository.deleteAll().then().block()
    }

    @Test
    fun `find by name`() {
        val name = "test"
        companyService.findByName(name).test()
                .consumeNextWith {
                    run {
                        Assertions.assertThat(it.name).isEqualTo("test")
                    }
                }.verifyComplete()
    }


//    @Test
//    fun `verify two identical documents`() {
//        val address = Address("18081260", "gentil", "sorocaba", "sp", 121)
//        val company = Company("test", address, "210381280", "1")
//        val error:Mono<Company> = Mono.error(DocumentExistsException("similar docs"))
//        val exception = companyService.save(company)
////        assertEquals(error, exception)
//        println(exception.block().toString())
//        companyService.save(company).test().consumeErrorWith() {
//            run {
//                println(it)
//            }
//        }
//    }

}