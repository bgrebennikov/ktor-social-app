package com.github.bgrebennikov.DI.modules

import com.github.bgrebennikov.services.auth.AuthService
import com.github.bgrebennikov.services.auth.AuthServiceImpl
import org.koin.dsl.module


val authServiceModule = module {
    single<AuthService> {
        AuthServiceImpl()
    }
}