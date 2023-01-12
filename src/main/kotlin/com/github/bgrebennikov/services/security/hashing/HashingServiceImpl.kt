package com.github.bgrebennikov.services.security.hashing

import com.github.bgrebennikov.common.HASHING_SALT_PROPERTY
import io.ktor.server.application.*
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HashingServiceImpl : HashingService, KoinComponent {

    private val application by inject<Application>()
    private val salt: String = application.environment.config.property(HASHING_SALT_PROPERTY).getString()

    override fun generateSaltedHash(value: String): String {
        val saltHex = Hex.encodeHexString(salt.toByteArray())
        return DigestUtils.sha256Hex("$saltHex$value")
    }

    override fun verify(password: String, passwordHash: String): Boolean {
        return generateSaltedHash(password) == passwordHash
    }
}