package com.github.bgrebennikov.data.entity.user

import org.bson.codecs.pojo.annotations.BsonId

data class UserEntity(
    @BsonId
    val id: String,
    val profile: UserProfile,
    val settings: UserSettings
) {

    data class UserSettings(
        val token: String,
        val passwordHash: String,
    )

    data class UserProfile(
        @BsonId
        val id: String,
        val email: String,
        val firstName: String?,
        val avatar: String? = null
    )
}