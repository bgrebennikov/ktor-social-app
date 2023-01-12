package com.github.bgrebennikov.DI.modules

import com.github.bgrebennikov.services.auth.AuthService
import com.github.bgrebennikov.services.auth.AuthServiceImpl
import com.github.bgrebennikov.services.security.hashing.HashingService
import com.github.bgrebennikov.services.security.hashing.HashingServiceImpl
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
    single<HashingService>{
        HashingServiceImpl()
    }
}