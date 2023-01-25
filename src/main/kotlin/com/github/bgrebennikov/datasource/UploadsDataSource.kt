package com.github.bgrebennikov.datasource

import com.github.bgrebennikov.data.uploads.PhotosEntity

interface UploadsDataSource {

    suspend fun save(entity: PhotosEntity) : PhotosEntity

    suspend fun findPhotoById(photoId: String) : PhotosEntity?

    suspend fun editParams(photoId: String, params: PhotosEntity.Params) : PhotosEntity?

}