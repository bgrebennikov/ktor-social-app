package com.github.bgrebennikov.routes.auth

import io.ktor.server.application.*
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
        call.respond("SignUp Route")
    }
}