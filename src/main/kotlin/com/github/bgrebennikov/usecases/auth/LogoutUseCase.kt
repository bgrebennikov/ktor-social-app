package com.github.bgrebennikov.usecases.auth

import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.responses.auth.LogoutResponse
import com.github.bgrebennikov.services.auth.AuthService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LogoutUseCase : KoinComponent {

    private val authService by inject<AuthService>()

    suspend operator fun invoke(userId: String, token: String) : BaseResponse<LogoutResponse>{
        return authService.logout(userId, token)
    }

}