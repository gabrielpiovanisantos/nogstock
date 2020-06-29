package com.gabriel.nogstock.repositories

import com.gabriel.nogstock.entities.Address
import com.gabriel.nogstock.entities.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.test.StepVerifier

@SpringBootTest
class UserRepositoryTests {

    @Autowired
    lateinit var userRepository: UserRepository

    @BeforeAll
    fun setUp() {
        val address = Address("18081260", "gentil", "sorocaba", "sp",121)
        userRepository.save(User("Gabriel", "Santos", address,
                "gabriel",
                "senha")
        ).
        then().
        block()
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