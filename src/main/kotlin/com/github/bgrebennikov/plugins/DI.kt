package com.github.bgrebennikov.plugins

import com.github.bgrebennikov.DI.modules.authServiceModule
import com.github.bgrebennikov.DI.modules.databaseModule
import com.github.bgrebennikov.DI.modules.userModule
import io.ktor.server.application.*
import org.koin.core.context.startKoin
import org.koin.dsl.module


fun Application.configureDI(){
    startKoin {
        modules(
            databaseModule,
            userModule,
            authServiceModule,

            module {
                single {
                    this@configureDI
                }
            }
        )
    }
}