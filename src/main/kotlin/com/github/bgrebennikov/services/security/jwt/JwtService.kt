package com.github.bgrebennikov.services.security.jwt

import com.github.bgrebennikov.data.requests.auth.LoginRequest
import com.github.bgrebennikov.data.requests.auth.SignupRequest

interface JwtService {

    suspend fun generateAccessToken(request: SignupRequest, userId: String) : String
    suspend fun generateAccessToken(request: LoginRequest, userId: String) : String
    suspend fun generateRefreshToken() : String

}