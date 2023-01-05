package com.github.bgrebennikov.plugins

import com.github.bgrebennikov.DI.modules.databaseModule
import io.ktor.server.application.*
import org.koin.core.context.startKoin


fun Application.configureDI(){
    startKoin {
        modules(
            databaseModule
        )
    }
}