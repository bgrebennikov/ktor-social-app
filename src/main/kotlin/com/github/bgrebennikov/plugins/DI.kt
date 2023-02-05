package com.github.bgrebennikov.plugins

import com.github.bgrebennikov.DI.modules.*
import io.ktor.server.application.*
import org.koin.core.context.startKoin
import org.koin.dsl.module


fun Application.configureDI(){
    startKoin {
        modules(
            databaseModule,
            userModule,
            authServiceModule,
            uploadModule,
            redisModule,
            searchModule,

            module {
                single {
                    this@configureDI
                }
            },
        )
    }
}