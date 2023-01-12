package com.github.bgrebennikov.services.auth.jwt

import com.github.bgrebennikov.data.requests.auth.SignupRequestDto

interface JwtService {

    suspend fun generateToken(request: SignupRequestDto, userId: String) : String

}