package com.github.bgrebennikov.services.security.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.interfaces.DecodedJWT
import com.github.bgrebennikov.common.*
import com.github.bgrebennikov.data.jwt.UserJwtPrincipal
import io.ktor.server.application.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class JwtServiceImpl : JwtService, KoinComponent {

    private val application by inject<Application>()
    private val jwtAudience = application.environment.config.property(JWT_ACCESS_TOKEN_AUDIENCE).getString()
    private val issuer = application.environment.config.property(JWT_ISSUER).getString()

    private val refreshTokenVerifier: JWTVerifier =
        JWT.require(JWT_REFRESH_ALGORITHM)
            .withAudience(jwtAudience)
            .withIssuer(issuer)
            .build()

    private fun buildAccessJwtToken(userJwt: UserJwtPrincipal): String {
        return JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(issuer)
            .withExpiresAt(userJwt.expireAt)
            .withClaim(CLAIM_USER_ID, userJwt.userId)
            .withClaim(CLAIM_EMAIL, userJwt.email)
            .sign(JWT_ALGORITHM)
    }

    private fun buildRefreshJwtToken(userJwt: UserJwtPrincipal): String {
        return JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(issuer)
            .withClaim(CLAIM_USER_ID, userJwt.userId)
            .withClaim(CLAIM_EMAIL, userJwt.email)
            .withExpiresAt(userJwt.expireAt)
            .sign(JWT_REFRESH_ALGORITHM)
    }

    override suspend fun decodeRefreshToken(refreshToken: String): DecodedJWT? {

        val decodedJWT = refreshTokenVerifier.verify(refreshToken)

        with(decodedJWT) {
            val userId = getClaim(FIELD_USER_ID) ?: return null
            val email = getClaim(FIELD_EMAIL) ?: return null
        }

        return decodedJWT
    }

    override suspend fun generateAccessToken(email: String, userId: String): String {
        val expireAt = Date(System.currentTimeMillis() + ONE_DAY)
        return buildAccessJwtToken(UserJwtPrincipal(userId, email, expireAt))
    }


    override suspend fun generateRefreshToken(email: String, userId: String): String {
        val expireAt = Date(System.currentTimeMillis() + ONE_MONTH)
        return buildRefreshJwtToken(UserJwtPrincipal(userId, email, expireAt))
    }
}