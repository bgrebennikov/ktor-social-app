package com.github.bgrebennikov.services.security.jwt

interface JwtService {

    suspend fun generateAccessToken(email: String, userId: String) : String
    suspend fun generateRefreshToken() : String

}