package com.gabriel.nogstock.controllers

import com.gabriel.nogstock.entities.User
import com.gabriel.nogstock.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
        val userService: UserService
) {

    @GetMapping(params = ["login"])
    fun getByLogin(@RequestParam login: String) = userService.findByLogin(login)

    @GetMapping
    fun getAll() = userService.findAll()

    @GetMapping("{id}")
    fun getById(@PathVariable id: String) = userService.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody user: User) = userService.save(user)

}