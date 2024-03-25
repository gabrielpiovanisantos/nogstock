package com.gabriel.nogstock.services

import com.gabriel.nogstock.entities.Address
import com.gabriel.nogstock.entities.Company
import com.gabriel.nogstock.repositories.CompanyRepository
import com.gabriel.nogstock.services.CompanyServiceTests.CompanyServiceTests.DOCUMENT
import com.gabriel.nogstock.services.CompanyServiceTests.CompanyServiceTests.NAME
import com.gabriel.nogstock.utils.DocumentExistsException
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.kotlin.test.test


@Suppress("ReactiveStreamsUnusedPublisher")
class CompanyServiceTests {

    private lateinit var service: CompanyService
    private lateinit var repository: CompanyRepository
    private lateinit var company: Company
    private lateinit var address: Address

    object CompanyServiceTests {
        const val DOCUMENT = "210381280"
        const val NAME = "test"
    }

    @BeforeAll
    fun setUp() {
        repository = mockk(relaxed = true)
        address = Address("18081260", "gentil", "sorocaba", "sp", 121)
        company = Company(NAME, address, DOCUMENT, "1")
        service = CompanyService(repository)
    }

    @Test
    fun `find by name`() {
        every { repository.findByName(NAME) } returns Mono.just(company)
        service.findByName(NAME).test().consumeNextWith {
            run {
                assertThat(it.name).isEqualTo(NAME)
            }
        }.verifyComplete()
    }

    @Test
    fun `given an already existing documenting WHEN saving another company THEN throw proper exception`() {
        every { repository.findByDocument(DOCUMENT) } returns Mono.just(company)
        val company = Company(NAME, address, DOCUMENT, "1")
        service.save(company).test().consumeErrorWith {
                assertThat(it).isInstanceOf(DocumentExistsException::class.java)
            }.verify()
    }
}