package com.github.bgrebennikov.data.mappers

import com.github.bgrebennikov.data.entity.prompts.HobbiesSearchEntity
import com.github.bgrebennikov.data.entity.user.UserEntity
import kotlin.reflect.KClass

fun List<HobbiesSearchEntity>.mapTo(type: KClass<UserEntity.Hobbies>) =
    this.map {
        UserEntity.Hobbies(
            id = it.id, title = it.title, emojiUnicode = it.emojiUnicode
        )
    }