package com.github.bgrebennikov.DI.modules

import com.github.bgrebennikov.services.security.AuthService
import com.github.bgrebennikov.services.security.AuthServiceImpl
import com.github.bgrebennikov.services.security.jwt.JwtService
import com.github.bgrebennikov.services.security.jwt.JwtServiceImpl
import org.koin.dsl.module


val authServiceModule = module {
    single<AuthService> {
        AuthServiceImpl()
    }

    single<JwtService> {
        JwtServiceImpl()
    }
}