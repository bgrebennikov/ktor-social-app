package com.github.bgrebennikov.services.security.jwt

import com.auth0.jwt.interfaces.DecodedJWT

interface JwtService {

    suspend fun generateAccessToken(email: String, userId: String) : String
    suspend fun generateRefreshToken(email: String, userId: String) : String

    suspend fun decodeRefreshToken(refreshToken: String) : DecodedJWT?

}