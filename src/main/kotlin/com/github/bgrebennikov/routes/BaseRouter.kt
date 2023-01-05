package com.github.bgrebennikov.plugins

import com.github.bgrebennikov.routes.auth.authRoutes
import com.github.bgrebennikov.routes.homeRoute
import io.ktor.server.routing.*

fun Route.baseRouter(){
    homeRoute()
    authRoutes()
}