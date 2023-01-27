package com.github.bgrebennikov.datasource

import com.github.bgrebennikov.data.auth.SettingsEntity

interface AuthDataSource {
    suspend fun insertSettings(settings: SettingsEntity) : SettingsEntity
    suspend fun findUserSettingsById(id: String) : SettingsEntity?
    suspend fun updatePassword(passwordHash: String, userId: String) : Boolean
}