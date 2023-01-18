package com.github.bgrebennikov.usecases.auth

import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.requests.auth.LoginRequestDto
import com.github.bgrebennikov.services.auth.AuthService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginUseCase : KoinComponent {

    private val authService by inject<AuthService>()

    suspend operator fun invoke(request: LoginRequestDto) : BaseResponse<UserEntity>{
        return authService.login(request)
    }

}