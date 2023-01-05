package com.github.bgrebennikov.data.entity.user

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class UserEntity(
    @BsonId
    val id: String,
    val token: String,
    val passwordHash: String,
    val profile: UserProfile
) {


    data class UserProfile(
        @BsonId
        val id: String,
        val firstName: String?
    )
}