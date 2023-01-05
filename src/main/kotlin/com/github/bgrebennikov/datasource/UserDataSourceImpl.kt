package com.github.bgrebennikov.datasource

import com.github.bgrebennikov.data.entity.user.UserEntity

class UserDataSourceImpl : UserDataSource {
    override suspend fun insertUser(user: UserEntity): UserEntity {
        TODO()
    }
}