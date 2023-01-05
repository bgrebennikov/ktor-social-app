package com.github.bgrebennikov.data.entity.user

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

open class UserId (open val id : String = ObjectId().toString())

data class UserEntity(
    @BsonId
    override val id: String,
    val token: String,
    val passwordHash: String,
    val profile: Test
) : UserId() {


    data class Test(
        override val id: String,
        val firstName: String?
    ) : UserId()
}