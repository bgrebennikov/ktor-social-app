package com.github.bgrebennikov.plugins

import com.github.bgrebennikov.common.UPLOADS_PATH_ROOT
import com.github.bgrebennikov.routes.auth.authRoutes
import com.github.bgrebennikov.routes.homeRoute
import com.github.bgrebennikov.routes.upload.uploadRoutes
import com.github.bgrebennikov.routes.user.userRoutes
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import java.io.File

fun Route.baseRouter(){
    homeRoute()
    authRoutes()

    serveStaticFiles()

    authenticate {
        userRoutes()
        uploadRoutes()
    }
}

private fun Route.serveStaticFiles(){
    static(UPLOADS_PATH_ROOT){
        staticRootFolder = File(UPLOADS_PATH_ROOT)
        files(".")
    }
}