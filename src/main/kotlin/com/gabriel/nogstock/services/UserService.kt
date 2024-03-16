package com.gabriel.nogstock.services

import com.gabriel.nogstock.entities.User
import com.gabriel.nogstock.repositories.UserRepository
import com.gabriel.nogstock.utils.DocumentExistsException
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

private const val ALREADY_A_USER_WITH_THE_DOCUMENT = "There is already a user with the document"

@Service
class UserService(
    val repository: UserRepository
) {
    fun save(user: User): Mono<User> =
        repository.findByDocument(user.document).flatMap<User> {
            Mono.error<User>(DocumentExistsException("$ALREADY_A_USER_WITH_THE_DOCUMENT${user.document}"))
        }.switchIfEmpty(
            repository.save(user)
        )

    fun getById(id: String): Mono<User> = repository.findById(id)

    fun findByLogin(login: String): Mono<User> = repository.findByLogin(login)
    fun findAll(): Flux<User> = repository.findAll()

}
