package com.github.bgrebennikov.datasource

import com.github.bgrebennikov.data.entity.user.UserEntity

interface UserDataSource{
    suspend fun insertUser(user: UserEntity) : UserEntity
}