package com.gabriel.nogstock.utils

class CompanyNotFoundException(override val message: String) : RuntimeException(message)