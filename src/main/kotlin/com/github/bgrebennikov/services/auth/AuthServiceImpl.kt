package com.github.bgrebennikov.services.auth

import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.requests.auth.SignupRequestDto

class AuthServiceImpl : AuthService {
    override suspend fun signUp(signupRequest: SignupRequestDto): UserEntity {
        TODO("Not yet implemented")
    }
}