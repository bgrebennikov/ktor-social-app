package com.github.bgrebennikov.datasource

import com.github.bgrebennikov.data.entity.user.UserEntity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.litote.kmongo.coroutine.CoroutineDatabase

class UserDataSourceImpl : UserDataSource, KoinComponent {

    private val database by inject<CoroutineDatabase>()

    private val users = database.getCollection<UserEntity>()

    override suspend fun insertUser(user: UserEntity): UserEntity {
        TODO()
    }
}