package com.github.bgrebennikov.services.security.jwt

import com.auth0.jwt.JWT
import com.github.bgrebennikov.common.*
import com.github.bgrebennikov.data.requests.auth.LoginRequestDto
import com.github.bgrebennikov.data.requests.auth.SignupRequestDto
import com.github.bgrebennikov.data.requests.auth.UserJwtPrincipal
import io.ktor.server.application.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Calendar
import java.util.Date

class JwtServiceImpl : JwtService, KoinComponent {

    private val application by inject<Application>()

    private val oneDay = 3_600_000 * 24
    private val oneMonth = Calendar.getInstance().apply {
        add(Calendar.MONTH, 1)
    }.time.time

    private val accessTokenExpiresAt = Date(System.currentTimeMillis() + oneDay)
    private val refreshTokenExpiresAt = Date(System.currentTimeMillis() + oneMonth)

    private val jwtAudience = application.environment.config.property(JWT_AUDIENCE).getString()
    private val issuer = application.environment.config.property(JWT_DOMAIN).getString()


    private fun buildAccessJwtToken(claim: UserJwtPrincipal, expiresAt: Date) : String{
        return JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(issuer)
            .withExpiresAt(expiresAt)
            .withClaim(CLAIM_USER_ID, claim.userId)
            .withClaim(CLAIM_EMAIL, claim.email)
            .sign(JWT_ALGORITHM)
    }

    private fun buildRefreshJwtToken(expiresAt: Date) : String{
        return JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(issuer)
            .withExpiresAt(expiresAt)
            .sign(JWT_ALGORITHM)
    }

    override suspend fun generateAccessToken(request: SignupRequestDto, userId: String): String {
        return buildAccessJwtToken(UserJwtPrincipal(userId, request.email), accessTokenExpiresAt)
    }

    override suspend fun generateAccessToken(request: LoginRequestDto, userId: String): String {
        return buildAccessJwtToken(UserJwtPrincipal(userId, request.login), accessTokenExpiresAt)
    }

    override suspend fun generateRefreshToken(): String {
        return buildRefreshJwtToken(refreshTokenExpiresAt)
    }
}