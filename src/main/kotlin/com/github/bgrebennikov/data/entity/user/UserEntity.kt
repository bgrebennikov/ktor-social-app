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
        val firstName: String,
        val lastName: String,
        val avatar: List<Avatar> = listOf(),
        val statusText: String? = null,
        val info: BasicInfo? = null,
        val hobbies: List<Hobbies> = listOf(),
        val about: String? = null
    )

    data class BasicInfo(
        val country: String,
        val city: String,
        val age: Int
    )

    data class Hobbies(
        val title: String,
        val emojiIconSource: String
    )

    data class Avatar(
        val src: String,
        val thumbnails: Map<String, String>
    )

}