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
        val hobbies: List<Hobbies> = listOf(),
        val about: About = About()
    )

    data class About(
        val place: Place? = null,
        val age: Int? = null,
        val myself: String? = null
    ){
        data class Place(
            @BsonId
            val id : String,
            val city_title: String,
            val country_title: String
        )
    }

    data class Hobbies(
        val id: String,
        val title: String,
        val emojiUnicode: String
    )

    data class Avatar(
        val src: String,
        val thumbnails: Map<String, String>
    )
}