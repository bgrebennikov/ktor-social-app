package com.github.bgrebennikov.services.security.hashing

interface HashingService {
    fun generateSaltedHash(value: String): String
    fun verify(password: String, passwordHash: String) : Boolean
}