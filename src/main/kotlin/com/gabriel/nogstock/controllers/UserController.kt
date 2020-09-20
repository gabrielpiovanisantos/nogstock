package com.gabriel.nogstock.controllers

import com.gabriel.nogstock.entities.User
import com.gabriel.nogstock.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/users")
class UserController(
        val userService: UserService
) {


//    @Autowired
//    lateinit var UserRepository: UserRepository

    @GetMapping()
    fun getByLogin(@RequestParam login: String): Mono<User> = userService.findByLogin(login)

    @GetMapping("{id}")
    fun getById(@PathVariable id: String): Mono<User> = userService.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody user: User): Mono<User> = userService.save(user)

}