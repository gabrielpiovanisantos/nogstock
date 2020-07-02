package com.gabriel.nogstock.services

import com.gabriel.nogstock.entities.User
import com.gabriel.nogstock.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    fun save(user: User) = userRepository.save(user)
    fun findByLogin(login: String): Mono<User> = userRepository.findByLogin(login)

}
