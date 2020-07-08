package com.gabriel.nogstock.services

import com.gabriel.nogstock.entities.Address
import com.gabriel.nogstock.entities.Company
import com.gabriel.nogstock.entities.User
import com.gabriel.nogstock.repositories.CompanyRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.kotlin.test.test
import reactor.test.StepVerifier

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


    @Test
    fun `verify two identical documents`() {
        val address = Address("18081260", "gentil", "sorocaba", "sp", 121)
        val company = Company("test", address, "210381280", "1")
        val exception = assertThrows<Exception>("Should throw an exception") {
            companyService.save(company)
        }
        assertEquals("the document must be unique", exception.message)
    }

}