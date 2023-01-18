package com.github.bgrebennikov.services.user

import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.entity.user.UserEntity

interface UserService {

    suspend fun init(userId: String?) : BaseResponse<UserEntity>

}