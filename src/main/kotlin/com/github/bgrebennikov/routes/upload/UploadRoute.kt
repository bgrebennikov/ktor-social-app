package com.github.bgrebennikov.routes.upload

import com.github.bgrebennikov.plugins.jwtUser
import com.github.bgrebennikov.usecases.upload.PhotoUploadUseCase
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.utils.io.jvm.javaio.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

fun Route.uploadRoutes(){

    route("/upload"){
        route("/photo"){
            photoUpload()
        }
    }

}

private fun Route.photoUpload(){
    post {

        val userId = call.jwtUser?.userId ?: return@post

        val photo : BufferedImage = withContext(Dispatchers.IO) {
            ImageIO.read(call.receiveChannel().toInputStream())
        }

        call.respond(PhotoUploadUseCase().invoke(photo, userId))
    }
}