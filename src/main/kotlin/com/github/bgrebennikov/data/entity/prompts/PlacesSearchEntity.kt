package com.github.bgrebennikov.data.entity.prompts

import org.bson.codecs.pojo.annotations.BsonId

data class PlacesSearchEntity(
    @BsonId
    val id: String,
    val city_title: String,
    val region_title: String,
    val country_title: String,
    val insertedByUser: Boolean = false
)