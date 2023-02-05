package com.github.bgrebennikov.datasource

import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.uploads.PhotosEntity
import com.mongodb.bulk.BulkWriteResult
import com.mongodb.client.result.UpdateResult
import org.bson.types.ObjectId
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase

class UserDataSourceImpl : UserDataSource, KoinComponent {

    private val database by inject<CoroutineDatabase>()

    private val users = database.getCollection<UserEntity>()

    override suspend fun findUserByEmail(email: String): UserEntity? {
        return users.findOne(
            UserEntity::profile / UserEntity.UserProfile::email eq email
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

    override suspend fun updateAbout(userId: String, about: UserEntity.About): BulkWriteResult {

        val newAboutValues = getUserAbout(userId)?.let {
            about.copy(
                place= about.place ?: it.place,
                age = about.age ?: it.age,
                myself = about.myself ?: it.myself
            )
        } ?: about

        return users.bulkWrite(
            updateOne(
                UserEntity::id eq userId,
                set(UserEntity::profile / UserEntity.UserProfile::about setTo newAboutValues)
            )

        )
    }

    override suspend fun getUserAbout(userId: String): UserEntity.About? {
        return users.findOne(
            UserEntity::id eq userId,
        )?.profile?.about
    }

    override suspend fun getUserHobbies(userId: String): List<UserEntity.Hobbies> {

        return users.findOne(UserEntity::id eq userId)?.profile?.hobbies ?: emptyList()
    }

    override suspend fun updateUserHobby(userId: String, hobbies: List<UserEntity.Hobbies>): UpdateResult {


        return users.updateOne(
            UserEntity::id eq ObjectId(userId).toString(),
            setValue(
                UserEntity::profile / UserEntity.UserProfile::hobbies,
                hobbies,
            ),
        )
    }

    override suspend fun insertUser(user: UserEntity): UserEntity {
        users.insertOne(user)
        return user
    }
}