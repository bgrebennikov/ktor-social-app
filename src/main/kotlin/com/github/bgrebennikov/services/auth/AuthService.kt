package com.github.bgrebennikov.services.auth

import com.github.bgrebennikov.data.auth.AuthResponse
import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.requests.auth.LoginRequestDto
import com.github.bgrebennikov.data.requests.auth.SignupRequestDto
import com.github.bgrebennikov.data.responses.auth.LogoutResponse

interface AuthService {

    suspend fun signUp(signupRequest: SignupRequestDto) : BaseResponse<AuthResponse>
    suspend fun login(loginRequestDto: LoginRequestDto) : BaseResponse<AuthResponse>

    suspend fun logout(userId: String, token: String) : BaseResponse<LogoutResponse>

    suspend fun tokenIsDenny(token: String?) : Boolean

}