package com.github.bgrebennikov.services.security.jwt

import com.github.bgrebennikov.data.requests.auth.LoginRequestDto
import com.github.bgrebennikov.data.requests.auth.SignupRequestDto

interface JwtService {

    suspend fun generateToken(request: SignupRequestDto, userId: String) : String
    suspend fun generateToken(request: LoginRequestDto, userId: String) : String

}