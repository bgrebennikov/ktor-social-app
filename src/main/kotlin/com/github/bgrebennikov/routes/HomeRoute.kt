package com.github.bgrebennikov.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.homeRoute(){
    route("/"){
        post {
            call.respond("Home route")
        }
    }
}