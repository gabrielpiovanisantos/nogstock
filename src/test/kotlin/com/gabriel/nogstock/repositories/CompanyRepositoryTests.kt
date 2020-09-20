package com.gabriel.nogstock.repositories

import com.gabriel.nogstock.entities.Address
import com.gabriel.nogstock.entities.Company
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import reactor.kotlin.test.test

@DataMongoTest
class CompanyRepositoryTests {
    @Autowired
    lateinit var companyRepository: CompanyRepository


    @BeforeAll
    fun setUp() {
        val address = Address("18081260", "gentil", "sorocaba", "sp", 121)
        val company = Company("test", address, "210381280", "1")
        companyRepository.save(company).then().block()
    }

    @AfterAll
    fun tearDown() {
        companyRepository.deleteAll().then().block()
    }

    @Test
    fun `find by name`() {
        companyRepository.findByName("test").test()
                .consumeNextWith {
                    run {
                        Assertions.assertThat(it.name).isEqualTo("test")
                    }
                }.verifyComplete()
    }
}