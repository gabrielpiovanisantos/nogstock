package com.gabriel.nogstock.controllers

import com.gabriel.nogstock.entities.User
import com.gabriel.nogstock.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    lateinit var userService: UserService

//    @Autowired
//    lateinit var UserRepository: UserRepository

    @GetMapping("{login}")
    fun getByLogin(@PathVariable login: String): Mono<User> = userService.findByLogin(login)

    @PostMapping
    fun save(@RequestBody user: User): Mono<User> = userService.save(user)

}