package com.gabriel.nogstock.repositories

import com.gabriel.nogstock.entities.Address
import com.gabriel.nogstock.entities.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import reactor.test.StepVerifier

@DataMongoTest
class UserRepositoryTests(
        private val userRepository: UserRepository

) {


    @BeforeAll
    fun setUp() {
        val address = Address("18081260", "gentil", "sorocaba", "sp", 121)
        val user = User("Gabriel", "Santos", "23214543534", address,
                "3424232355",
                "gabriel@teste.com",
                "gabriel",
                "senha")
        userRepository.save(user)
                .then().block()
    }

    @AfterAll
    fun tearDown() {
        userRepository.deleteAll().then().block()
    }

    @Test
    fun `find by last name`() {
        StepVerifier.create(userRepository.findByLastName("Santos"))
                .consumeNextWith {
                    run {
                        assertThat(it.firstName).isEqualTo("Gabriel")
                    }
                }.verifyComplete()

    }


}