package com.github.bgrebennikov.datasource

import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.uploads.PhotosEntity
import com.mongodb.client.model.Filters
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase

class UserDataSourceImpl : UserDataSource, KoinComponent {

    private val database by inject<CoroutineDatabase>()

    private val users = database.getCollection<UserEntity>()
    override suspend fun findUserByEmail(email: String): UserEntity? {
        return users.findOne(
            Filters.eq("profile.email", email)
        )
    }

    override suspend fun findUserById(userId: String): UserEntity? {
        return users.findOneById(userId)
    }

    override suspend fun updateUserAvatar(userId: String, source: PhotosEntity) : Boolean{
        val upd = users.updateOne(
            UserEntity::id eq userId,
            setValue(UserEntity::profile / UserEntity.UserProfile::avatar, source.src)
        )

        return upd.wasAcknowledged()
    }

    override suspend fun insertUser(user: UserEntity): UserEntity {
        users.insertOne(user)
        return user
    }
}