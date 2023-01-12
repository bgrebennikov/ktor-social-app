package com.github.bgrebennikov.services.security.hashing

class HashingServiceImpl : HashingService {
    override fun generateSaltedHash(value: String): String {
        TODO("Not yet implemented")
    }

    override fun verify(password: String, passwordHash: String): Boolean {
        TODO("Not yet implemented")
    }
}