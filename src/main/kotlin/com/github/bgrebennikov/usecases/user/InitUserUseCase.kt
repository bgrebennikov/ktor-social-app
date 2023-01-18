package com.github.bgrebennikov.usecases.user

import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.services.auth.AuthService
import com.github.bgrebennikov.services.user.UserService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class InitUserUseCase : KoinComponent {

    private val userService by inject<UserService>()

    suspend operator fun invoke(uid: String?) : BaseResponse<UserEntity>{
        return userService.init(userId = uid)
    }

}