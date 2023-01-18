package com.github.bgrebennikov.routes.user

import com.github.bgrebennikov.plugins.jwtUser
import com.github.bgrebennikov.usecases.user.InitUserUseCase
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes(){
    route("user/init"){
        initUser()
    }
}

private fun Route.initUser(){
    post {
        call.respond(InitUserUseCase().invoke(call.jwtUser?.userId))
    }
}