package com.gabriel.nogstock.services

import com.gabriel.nogstock.entities.Address
import com.gabriel.nogstock.entities.User
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.test.StepVerifier

@SpringBootTest
class UserServiceTests {

    @Autowired
    lateinit var userService: UserService

    @BeforeAll
    fun setUp() {
        val address = Address("18081260", "gentil", "sorocaba", "sp", 121)
        userService.save(User("Gabriel", "Santos", address,
                "gabriel",
                "senha")
        ).then().block()
    }

    @AfterAll
    fun tearDown() {
        userService.userRepository.deleteAll()
    }

    @Test
    fun `find by login`() {
        StepVerifier.create(userService.findByLogin("gabriel"))
                .consumeNextWith {
                    run {
                        Assertions.assertThat(it.login).isEqualTo("gabriel")
                    }
                }.verifyComplete()
    }

}