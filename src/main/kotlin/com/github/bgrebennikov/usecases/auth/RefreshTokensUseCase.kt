package com.github.bgrebennikov.usecases.auth

import com.github.bgrebennikov.common.Errors
import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.responses.auth.AuthResponse
import com.github.bgrebennikov.services.auth.AuthService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RefreshTokensUseCase : KoinComponent {

    private val authService by inject<AuthService>()

    suspend operator fun invoke(refreshToken: String?) : BaseResponse<AuthResponse> {
        refreshToken ?: return Errors.Auth.INVALID_REFRESH_TOKEN

        return authService.refresh(refreshToken)
    }

}