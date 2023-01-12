package com.github.bgrebennikov.services.auth.jwt

import com.github.bgrebennikov.common.JWT_AUDIENCE
import com.github.bgrebennikov.common.JWT_DOMAIN
import com.github.bgrebennikov.data.requests.auth.SignupRequestDto
import io.ktor.server.application.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Date

class JwtServiceImpl : JwtService, KoinComponent {

    private val application by inject<Application>()

    private val maxHours = 3_600_000 * 24
    private val expiresAt = Date(System.currentTimeMillis() + maxHours)
    private val jwtAudience = application.environment.config.property(JWT_AUDIENCE).getString()
    private val issuer = application.environment.config.property(JWT_DOMAIN).getString()

    override suspend fun generateToken(request: SignupRequestDto, userId: String): String {
        TODO()
    }
}