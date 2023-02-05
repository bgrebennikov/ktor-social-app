package com.github.bgrebennikov.datasource

import com.github.bgrebennikov.data.uploads.PhotosEntity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.setValue

class UploadsDataSourceImpl : UploadsDataSource, KoinComponent {

    private val database by inject<CoroutineDatabase>()
    private val photos = database.getCollection<PhotosEntity>()

    override suspend fun save(entity: PhotosEntity): PhotosEntity {
        photos.insertOne(entity)
        return entity
    }

    override suspend fun findPhotoById(photoId: String): PhotosEntity? {
        return photos.findOne(
            PhotosEntity::id eq  photoId
        )
    }

    override suspend fun editParams(photoId: String, params: PhotosEntity.Params): PhotosEntity? {

        val source = findPhotoById(photoId) ?: return null

        val insertResult = photos.updateOne(
            PhotosEntity::id eq photoId,
            setValue(PhotosEntity::params , params)
        ).wasAcknowledged()

        if (!insertResult) return null

        return source
    }
}