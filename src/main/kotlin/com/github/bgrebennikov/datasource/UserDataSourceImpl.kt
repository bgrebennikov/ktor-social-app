package com.github.bgrebennikov.datasource

import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.uploads.PhotosEntity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.div
import org.litote.kmongo.eq
import org.litote.kmongo.push

class UserDataSourceImpl : UserDataSource, KoinComponent {

    private val database by inject<CoroutineDatabase>()

    private val users = database.getCollection<UserEntity>()
    override suspend fun findUserByEmail(email: String): UserEntity? {
        return users.findOne(
            UserEntity.UserProfile::email eq email
        )
    }

    override suspend fun findUserById(userId: String): UserEntity? {
        return users.findOneById(userId)
    }

    override suspend fun updateUserAvatar(userId: String, source: PhotosEntity): Boolean {
        val upd = users.updateOne(
            UserEntity::id eq userId,
            push(
                UserEntity::profile / UserEntity.UserProfile::avatar,
                UserEntity.Avatar(
                    source.src,
                    source.thumbnails
                )
            )
        )

        return upd.wasAcknowledged()
    }

    override suspend fun insertUser(user: UserEntity): UserEntity {
        users.insertOne(user)
        return user
    }
}