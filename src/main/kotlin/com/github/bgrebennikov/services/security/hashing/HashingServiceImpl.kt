package com.github.bgrebennikov.services.security.hashing

import com.github.bgrebennikov.common.HASHING_SALT_PROPERTY
import io.ktor.server.application.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HashingServiceImpl : HashingService, KoinComponent {

    private val application by inject<Application>()
    private val salt: String = application.environment.config.property(HASHING_SALT_PROPERTY).getString()

    override fun generateSaltedHash(value: String): String {
        TODO("Not yet implemented")
    }

    override fun verify(password: String, passwordHash: String): Boolean {
        TODO("Not yet implemented")
    }
}