package com.github.bgrebennikov.services.user

import com.github.bgrebennikov.common.Errors
import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.datasource.UserDataSource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserServiceImpl : UserService, KoinComponent {

    private val userDataSource by inject<UserDataSource>()

    override suspend fun init(userId: String?): BaseResponse<UserEntity> {
        val user = userDataSource.findUserById(userId.toString()) ?: return Errors.User.USER_NOT_FOUND

        return BaseResponse(
            response = user
        )
    }
}