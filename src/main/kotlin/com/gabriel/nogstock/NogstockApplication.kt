package com.gabriel.nogstock

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NogstockApplication

fun main(args: Array<String>) {
    runApplication<NogstockApplication>(*args){
    }
}
