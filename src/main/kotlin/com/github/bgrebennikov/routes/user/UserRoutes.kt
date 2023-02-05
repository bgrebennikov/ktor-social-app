package com.github.bgrebennikov.routes.user

import com.github.bgrebennikov.data.requests.user.edit.EditAboutRequest
import com.github.bgrebennikov.data.requests.user.edit.EditAvatarRequest
import com.github.bgrebennikov.data.requests.user.edit.EditHobbiesRequest
import com.github.bgrebennikov.plugins.jwtUser
import com.github.bgrebennikov.usecases.user.InitUserUseCase
import com.github.bgrebennikov.usecases.user.edit.EditAboutUseCase
import com.github.bgrebennikov.usecases.user.edit.EditAvatarUseCase
import com.github.bgrebennikov.usecases.user.edit.EditHobbiesUseCase
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes(){
    route("user"){
        route("init"){
            initUser()
        }
        route("edit"){
            route("avatar"){
                editAvatar()
            }
            route("hobbies"){
                editHobbies()
            }
            route("about"){
                editAbout()
            }
        }
    }
}

private fun Route.initUser(){
    post {
        call.respond(InitUserUseCase().invoke(call.jwtUser?.userId))
    }
}

private fun Route.editAvatar(){
    post {

        val request = call.receiveNullable<EditAvatarRequest>() ?: return@post
        call.respond(EditAvatarUseCase().invoke(request, call.jwtUser?.userId))
    }
}

private fun Route.editHobbies(){
    post {

        val userId = call.jwtUser?.userId ?: return@post
        val request = call.receiveNullable<EditHobbiesRequest>() ?: return@post

        call.respond(
            EditHobbiesUseCase().invoke(userId, request)
        )
    }
}

private fun Route.editAbout(){
    post {
        val userId = call.jwtUser?.userId ?: return@post
        val request = call.receiveNullable<EditAboutRequest>() ?: return@post

        call.respond(EditAboutUseCase().invoke(userId, request))

    }
}