package com.github.bgrebennikov.DI.modules

import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val databaseModule = module {
    single {
        KMongo
            .createClient(
                "mongodb+srv://ivUser:Icu25050032&$@cluster0.ueh7iy4.mongodb.net/test",
            )
            .coroutine
            .getDatabase("SocialApp")
    }
}