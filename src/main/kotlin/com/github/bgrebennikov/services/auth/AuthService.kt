package com.github.bgrebennikov.services.auth

import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.requests.auth.LoginRequest
import com.github.bgrebennikov.data.requests.auth.SignupRequest
import com.github.bgrebennikov.data.responses.auth.AuthResponse
import com.github.bgrebennikov.data.responses.auth.LogoutResponse

interface AuthService {

    suspend fun signUp(signupRequest: SignupRequest) : BaseResponse<AuthResponse>
    suspend fun login(loginRequest: LoginRequest) : BaseResponse<AuthResponse>
    suspend fun refresh(refreshToken: String): BaseResponse<AuthResponse>
    suspend fun logout(userId: String, token: String) : BaseResponse<LogoutResponse>

    fun tokenInBlacklist(token: String?) : Boolean

}