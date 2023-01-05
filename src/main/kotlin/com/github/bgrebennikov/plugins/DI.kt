package com.github.bgrebennikov.plugins

import io.ktor.server.application.*
import org.koin.core.context.startKoin


fun Application.configureDI(){
    startKoin {

    }
}