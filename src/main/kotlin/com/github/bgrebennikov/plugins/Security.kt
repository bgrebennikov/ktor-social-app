package com.github.bgrebennikov.plugins

import com.github.bgrebennikov.common.*
import com.github.bgrebennikov.data.jwt.UserJwtPrincipal
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*

fun Application.configureSecurity() {

    val jwtConfig = JwtConfig( this)
    val jwtRealm = this.environment.config.property(JWT_ACCESS_TOKEN_REALM).getString()

    authentication {
        jwt(ACCESS_TOKEN) {
            realm = jwtRealm
            verifier(
                jwtConfig.accessTokenVerifier
            )
            validate { credentials ->
                jwtConfig.validateCredentials(this, credentials)
            }
            challenge { _, _ ->
                call.respond(HttpStatusCode.Unauthorized, Errors.Auth.TOKEN_EXPIRED)
            }
        }
    }
}

val ApplicationCall.jwtUser get() = principal<UserJwtPrincipal>()
val ApplicationCall.userToken get() = request.authorization()?.split(" ")?.last()