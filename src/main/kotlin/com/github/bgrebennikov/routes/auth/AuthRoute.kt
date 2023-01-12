package com.github.bgrebennikov.routes.auth

import com.github.bgrebennikov.data.requests.auth.SignupRequestDto
import com.github.bgrebennikov.usecases.auth.SignupUseCase
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRoutes() {

    route("/auth") {
        loginRoute()
        signUpRoute()
    }
}

private fun Route.loginRoute() {
    post("/login") {
        call.respond("Login Route")
    }
}

private fun Route.signUpRoute() {
    post("/signup") {

        val request = call.receiveNullable<SignupRequestDto>() ?: return@post
        call.respond(SignupUseCase().invoke(request))

    }
}