package com.github.bgrebennikov.DI.modules

import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo


const val DATABASE_INSTANCE_NAME = "dev"

val databaseModule = module {
    single {
        KMongo
            .createClient()
            .coroutine
            .getDatabase("SocialApp")
    }
}