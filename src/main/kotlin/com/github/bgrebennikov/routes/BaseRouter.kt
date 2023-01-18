package com.github.bgrebennikov.plugins

import com.github.bgrebennikov.routes.auth.authRoutes
import com.github.bgrebennikov.routes.homeRoute
import com.github.bgrebennikov.routes.user.userRoutes
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Route.baseRouter(){
    homeRoute()
    authRoutes()

    authenticate {
        userRoutes()
    }
}