package com.github.bgrebennikov.usecases.auth

import com.github.bgrebennikov.data.responses.auth.AuthResponse
import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.requests.auth.SignupRequest
import com.github.bgrebennikov.services.auth.AuthService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignupUseCase : KoinComponent{

    private val authService by inject<AuthService>()
    suspend operator fun invoke(request: SignupRequest) : BaseResponse<AuthResponse>{
        return authService.signUp(request)
    }

}