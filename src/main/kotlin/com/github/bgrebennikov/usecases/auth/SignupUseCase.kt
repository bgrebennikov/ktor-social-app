package com.github.bgrebennikov.usecases.auth

import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.requests.auth.SignupRequestDto

class SignupUseCase {

    suspend operator fun invoke(request: SignupRequestDto) : BaseResponse<UserEntity>{

        TODO()

    }

}