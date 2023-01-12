package com.github.bgrebennikov.services.auth.jwt

import com.github.bgrebennikov.data.requests.auth.SignupRequestDto

class JwtServiceImpl : JwtService {



    override suspend fun generateToken(request: SignupRequestDto, userId: String): String {
        TODO()
    }
}