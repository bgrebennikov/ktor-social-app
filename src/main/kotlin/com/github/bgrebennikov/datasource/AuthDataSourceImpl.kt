package com.github.bgrebennikov.datasource

import com.github.bgrebennikov.data.auth.SettingsEntity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.setValue
import org.litote.kmongo.upsert

class AuthDataSourceImpl : AuthDataSource, KoinComponent {

    private val database by inject<CoroutineDatabase>()
    private val settings = database.getCollection<SettingsEntity>()

    override suspend fun insertSettings(settings: SettingsEntity): SettingsEntity {
        this.settings.insertOne(settings)
        return settings
    }

    override suspend fun findUserSettingsById(id: String): SettingsEntity? {
        return settings.findOneById(id)
    }

    override suspend fun updatePassword(passwordHash: String, userId: String): Boolean {
        val upd = settings.updateOne(
            SettingsEntity::id eq userId,
            setValue(SettingsEntity::passwordHash, passwordHash),
            upsert()
        )
        return upd.wasAcknowledged()
    }
}