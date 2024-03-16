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
        val user = User("Gabriel", "Santos", "23214543534", address,
                "3424232355",
                "gabriel@teste.com",
                "gabriel",
                "senha")
        userService.save(user).then().block()
    }

    @AfterAll
    fun tearDown() {
        userService.repository.deleteAll().then().block()
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

//    @Test
//    fun `verify two identical logins`() {
//        val address = Address("18081260", "gentil", "sorocaba", "sp", 121)
//        val exception = assertThrows<Exception> ("Should throw an exception") {
//            userService.save(User("Gabriel", "Santos", "3454354464", address,
//                    "gabriel",
//                    "senha")
//            )
//        }
//        assertEquals("the login must be unique", exception.message)
//    }

//    @Test
//    fun `verify two identical documents`() {
//        val address = Address("18081260", "gentil", "sorocaba", "sp", 121)
//        val exception = assertThrows<Exception> ("Should throw an exception") {
//            userService.save(User("Gabriel", "Santos", "3454354464", address,
//                    "gabriel2",
//                    "senha2132")
//            )
//        }
//        assertEquals("the document must be unique", exception.message)
//    }


}