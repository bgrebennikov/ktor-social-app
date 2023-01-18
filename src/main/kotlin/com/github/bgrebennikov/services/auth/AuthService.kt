package com.github.bgrebennikov.services.auth

import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.requests.auth.LoginRequestDto
import com.github.bgrebennikov.data.requests.auth.SignupRequestDto

interface AuthService {

    suspend fun signUp(signupRequest: SignupRequestDto) : BaseResponse<UserEntity>
    suspend fun login(loginRequestDto: LoginRequestDto) : BaseResponse<UserEntity>

}