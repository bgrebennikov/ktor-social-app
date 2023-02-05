package com.github.bgrebennikov.routes.auth

import com.github.bgrebennikov.common.ACCESS_TOKEN
import com.github.bgrebennikov.data.requests.auth.LoginRequest
import com.github.bgrebennikov.data.requests.auth.RefreshTokenRequest
import com.github.bgrebennikov.data.requests.auth.SignupRequest
import com.github.bgrebennikov.plugins.jwtUser
import com.github.bgrebennikov.plugins.userToken
import com.github.bgrebennikov.usecases.auth.LoginUseCase
import com.github.bgrebennikov.usecases.auth.LogoutUseCase
import com.github.bgrebennikov.usecases.auth.RefreshTokensUseCase
import com.github.bgrebennikov.usecases.auth.SignupUseCase
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRoutes() {

    route("/auth") {
        loginRoute()
        signUpRoute()

        route("/refresh") {
            refresh()
        }

    }
    authenticate(ACCESS_TOKEN) {
        route("/logout") {
            logoutUser()
        }
    }
}

private fun Route.loginRoute() {
    post("/login") {
        val request = call.receiveNullable<LoginRequest>() ?: return@post
        call.respond(LoginUseCase().invoke(request))
    }
}

private fun Route.signUpRoute() {
    post("/signup") {

        val request = call.receiveNullable<SignupRequest>() ?: return@post
        call.respond(SignupUseCase().invoke(request))

    }
}

private fun Route.refresh() {
    post {
        val refreshToken = call.receiveNullable<RefreshTokenRequest>()

        call.respond(
            RefreshTokensUseCase().invoke(
                refreshToken?.refreshToken
            )
        )
    }
}

private fun Route.logoutUser() {
    post {

        val userId = call.jwtUser?.userId ?: return@post
        val token = call.userToken ?: return@post

        call.respond(LogoutUseCase().invoke(userId, token))

    }
}