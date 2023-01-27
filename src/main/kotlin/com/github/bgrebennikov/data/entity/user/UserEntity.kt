package com.github.bgrebennikov.data.entity.user

import org.bson.codecs.pojo.annotations.BsonId

data class UserEntity(
    @BsonId
    val id: String,
    val profile: UserProfile,
) {

    data class UserProfile(
        @BsonId
        val id: String,
        val email: String,
        val firstName: String?,
        val avatar: String? = null
    )
}