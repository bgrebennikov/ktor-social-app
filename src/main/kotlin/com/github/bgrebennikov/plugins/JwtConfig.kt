package com.github.bgrebennikov.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.github.bgrebennikov.common.*
import com.github.bgrebennikov.data.jwt.UserJwtPrincipal
import com.github.bgrebennikov.services.auth.AuthService
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class JwtConfig(
    private val application: Application
) : KoinComponent {


    private val authService: AuthService by inject()

    private val jwtAudience = application.environment.config.property(JWT_ACCESS_TOKEN_AUDIENCE).getString()
    private val issuer = application.environment.config.property(JWT_ISSUER).getString()

    val accessTokenVerifier: JWTVerifier =
        JWT.require(JWT_ALGORITHM)
            .withAudience(jwtAudience)
            .withIssuer(issuer)
            .build()


    fun validateCredentials(call: ApplicationCall, credentials: JWTCredential): Principal? {

        if (credentials.payload.audience.contains(jwtAudience) && !authService.tokenInBlacklist(call.userToken)) {
            with(credentials.payload) {
                val userId = getClaim(FIELD_USER_ID) ?: return null
                val email = getClaim(FIELD_EMAIL) ?: return null

                return UserJwtPrincipal(
                    userId = userId.asString(),
                    email = email.asString(),
                    expireAt = expiresAt
                )
            }
        }
        return null

    }
}