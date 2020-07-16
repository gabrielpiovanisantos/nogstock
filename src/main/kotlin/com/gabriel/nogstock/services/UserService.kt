package com.gabriel.nogstock.services

import com.gabriel.nogstock.entities.User
import com.gabriel.nogstock.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(
        val userRepository: UserRepository
) {


    fun save(user: User): Mono<User> {
//        val verifyUserLogin = userRepository.findByLogin(user.login)
//        val blockedUserLogin = verifyUserLogin.block()
//        if (blockedUserLogin != null && blockedUserLogin.login == user.login) throw Exception("the login must be unique")
//        val verifyUserDocument = userRepository.findByDocument(user.document)
//        val blockedUserDocument = verifyUserDocument.block()
//        if (blockedUserDocument != null && blockedUserDocument.document == user.document) throw Exception("the document must be unique")
        return userRepository.save(user)

    }

    fun getById(id: String): Mono<User> = userRepository.findById(id)

    fun findByLogin(login: String): Mono<User> = userRepository.findByLogin(login)

}
