package com.github.bgrebennikov.plugins

import com.auth0.jwt.JWT
import com.github.bgrebennikov.common.*
import com.github.bgrebennikov.data.requests.auth.UserJwtPrincipal
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {

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
                if (credential.payload.audience.contains(jwtAudience)) {
                    with(credential.payload) {
                        UserJwtPrincipal(
                            userId = getClaim(FIELD_USER_ID).asString(),
                            email = getClaim(FIELD_EMAIL).asString()
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