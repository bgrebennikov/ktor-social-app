package com.github.bgrebennikov.data.mappers

import com.github.bgrebennikov.data.entity.prompts.PlacesSearchEntity
import com.github.bgrebennikov.data.entity.user.UserEntity
import kotlin.reflect.KClass

fun PlacesSearchEntity.mapTo(
    type: KClass<UserEntity.About.Place>
) = UserEntity.About.Place(
        id = this.id,
        city_title = this.city_title,
        country_title = this.country_title
    )