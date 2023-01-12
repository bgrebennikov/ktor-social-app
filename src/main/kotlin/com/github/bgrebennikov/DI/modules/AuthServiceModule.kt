package com.github.bgrebennikov.DI.modules

import com.github.bgrebennikov.services.auth.AuthService
import com.github.bgrebennikov.services.auth.AuthServiceImpl
import com.github.bgrebennikov.services.auth.jwt.JwtService
import com.github.bgrebennikov.services.auth.jwt.JwtServiceImpl
import org.koin.dsl.module


val authServiceModule = module {
    single<AuthService> {
        AuthServiceImpl()
    }

    single<JwtService> {
        JwtServiceImpl()
    }
}