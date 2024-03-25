package com.gabriel.nogstock.services

import com.gabriel.nogstock.entities.Address
import com.gabriel.nogstock.entities.Company
import com.gabriel.nogstock.repositories.CompanyRepository
import com.gabriel.nogstock.utils.DocumentExistsException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.kotlin.test.test

private const val DOCUMENT = "210381280"

@SpringBootTest
class CompanyServiceTests {

    @Autowired
    lateinit var service: CompanyService

    @Autowired
    lateinit var companyRepository: CompanyRepository

    @Autowired
    lateinit var userService: UserService

    private val address1: Address
        get() {
            val address = Address("18081260", "gentil", "sorocaba", "sp", 121)
            return address
        }


    @BeforeAll
    fun setUp() {
        val company = Company("test", address1, DOCUMENT, "1")
        companyRepository.save(company).then().block()
    }

    @AfterAll
    fun tearDown() {
        service.repository.deleteAll().then().block()
        userService.repository.deleteAll().then().block()
    }

    @Test
    fun `find by name`() {
        val name = "test"
        service.findByName(name).test().consumeNextWith {
            run {
                assertThat(it.name).isEqualTo(name)
            }
        }.verifyComplete()
    }
    @Test
    fun `given an already existing documenting WHEN saving another company THEN throw proper exception`() {
        val company = Company("test", address1, DOCUMENT, "1")
        service.save(company).subscribe { assertThat(it).isInstanceOf(DocumentExistsException::class.java) }
    }

}