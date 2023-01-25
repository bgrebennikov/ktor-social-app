package com.github.bgrebennikov.services.photos

import com.github.bgrebennikov.common.SERVER_DOMAIN
import com.github.bgrebennikov.common.UPLOADS_PATH_ROOT
import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.uploads.PhotosEntity
import com.github.bgrebennikov.datasource.UploadsDataSource
import com.github.bgrebennikov.utils.ImageUtils
import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class UploadServiceImpl : UploadService, KoinComponent {

    private val application by inject<Application>()
    private val domain = application.environment.config
        .property(SERVER_DOMAIN).getString()

    private val uploadsDataSource by inject<UploadsDataSource>()

    private suspend fun saveEntity(uploadEntity: PhotosEntity): PhotosEntity {
        return uploadsDataSource.save(uploadEntity)
    }

    override suspend fun uploadPhoto(photo: BufferedImage, userId: String): BaseResponse<PhotosEntity> {
        val thumbnails = ImageUtils.createThumbnails(photo)

        val photoUploadDirPath = "$UPLOADS_PATH_ROOT/users/$userId/photos/${System.currentTimeMillis()}"
        val photoUploadDir = File(photoUploadDirPath)
        photoUploadDir.mkdirs()

        val outputFilePath = "${photoUploadDirPath}/photo_${System.currentTimeMillis()}.jpg"
        val photoOutputFile = File(outputFilePath)

        withContext(Dispatchers.IO) {
            ImageIO.write(photo, "jpg", photoOutputFile)
        }

        val thumbnailSources = mutableMapOf<String, String>()

        thumbnails.forEach { thumb ->
            val uploadDate = System.currentTimeMillis()
            val outputFile = File("${photoUploadDirPath}/thumbnails/thumbnail_$uploadDate.jpg")
            outputFile.mkdirs()
            val thumbnailOutputPath = "$domain/$photoUploadDirPath/thumbnails/${outputFile.name}"
            ImageIO.write(thumb.value, "jpg", outputFile)
            thumbnailSources[thumb.key] = thumbnailOutputPath
        }

        val response = saveEntity(
            PhotosEntity(
                path = "/$outputFilePath",
                src = "$domain/$outputFilePath",
                thumbnails = thumbnailSources,
                ownerId = userId
            )
        )

        return BaseResponse(
            response = response
        )

    }
}