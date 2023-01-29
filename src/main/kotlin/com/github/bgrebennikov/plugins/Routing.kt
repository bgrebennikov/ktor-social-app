package com.github.bgrebennikov.plugins

import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(Resources)

    routing {
        baseRouter()
    }
}
