package com.github.bgrebennikov.datasource

import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.uploads.PhotosEntity
import com.mongodb.bulk.BulkWriteResult
import com.mongodb.client.result.UpdateResult

interface UserDataSource{
    suspend fun insertUser(user: UserEntity) : UserEntity
    suspend fun findUserByEmail(email : String) : UserEntity?
    suspend fun findUserById(userId: String) : UserEntity?

    suspend fun updateUserAvatar(userId: String, source: PhotosEntity) : Boolean

    suspend fun updateAbout(userId: String, about : UserEntity.About) : BulkWriteResult

    suspend fun getUserAbout(userId: String) : UserEntity.About?
    suspend fun getUserHobbies(userId: String) : List<UserEntity.Hobbies>
    suspend fun updateUserHobby(userId: String, hobbies: List<UserEntity.Hobbies>) : UpdateResult

}