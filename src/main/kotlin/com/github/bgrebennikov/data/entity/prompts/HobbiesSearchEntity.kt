package com.github.bgrebennikov.data.entity.prompts

import org.bson.codecs.pojo.annotations.BsonId

data class HobbiesSearchEntity(
    @BsonId
    val id : String,
    val title: String,
    val emojiUnicode: String,
)