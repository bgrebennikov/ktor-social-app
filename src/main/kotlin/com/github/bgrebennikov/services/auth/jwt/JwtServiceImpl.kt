package com.github.bgrebennikov.services.auth.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.github.bgrebennikov.common.CLAIM_EMAIL
import com.github.bgrebennikov.common.CLAIM_USER_ID
import com.github.bgrebennikov.common.JWT_AUDIENCE
import com.github.bgrebennikov.common.JWT_DOMAIN
import com.github.bgrebennikov.data.requests.auth.SignupRequestDto
import com.github.bgrebennikov.data.requests.auth.UserJwtPrincipal
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


    private suspend fun buildJwtToken(claim: UserJwtPrincipal) : String{
        return JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(issuer)
            .withExpiresAt(expiresAt)
            .withClaim(CLAIM_USER_ID, claim.userId)
            .withClaim(CLAIM_EMAIL, claim.email)
            .sign(Algorithm.HMAC256("secret"))
    }

    override suspend fun generateToken(request: SignupRequestDto, userId: String): String {
        TODO()
    }
}