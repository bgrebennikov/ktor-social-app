package com.github.bgrebennikov.data.entity.settings

import org.bson.codecs.pojo.annotations.BsonId

data class SettingsEntity(
    @BsonId
    val id: String,
    val passwordHash: String,
    val lastUpdate: Long = System.currentTimeMillis()
)
