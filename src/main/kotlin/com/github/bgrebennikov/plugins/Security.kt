package com.github.bgrebennikov.plugins

import com.auth0.jwt.JWT
import com.github.bgrebennikov.common.*
import com.github.bgrebennikov.data.requests.auth.UserJwtPrincipal
import com.github.bgrebennikov.services.auth.AuthService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.ktor.ext.inject

fun Application.configureSecurity() {

    val authService by inject<AuthService>()

    authentication {
        jwt {
            val jwtAudience = this@configureSecurity.environment.config.property(JWT_AUDIENCE).getString()
            realm = this@configureSecurity.environment.config.property(JWT_REALM).getString()
            verifier(
                JWT
                    .require(JWT_ALGORITHM)
                    .withAudience(jwtAudience)
                    .withIssuer(this@configureSecurity.environment.config.property(JWT_DOMAIN).getString())
                    .build()
            )
            validate { credential ->

                if (authService.tokenIsDenny(userToken)) return@validate null

                if (credential.payload.audience.contains(jwtAudience)) {
                    with(credential.payload) {

                        val userId = getClaim(FIELD_USER_ID) ?: return@validate null
                        val email = getClaim(FIELD_EMAIL) ?: return@validate null

                        UserJwtPrincipal(
                            userId = userId.asString(),
                            email = email.asString()
                        )
                    }
                } else null
            }
            challenge { _, _ ->
                call.respond(HttpStatusCode.Unauthorized, Errors.Auth.TOKEN_EXPIRED)
            }
        }
    }

}


val ApplicationCall.jwtUser get() = principal<UserJwtPrincipal>()
val ApplicationCall.userToken get() = request.authorization()?.split(" ")?.last()