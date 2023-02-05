package com.github.bgrebennikov.usecases.upload

import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.uploads.PhotosEntity
import com.github.bgrebennikov.services.photos.UploadService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.awt.image.BufferedImage

class PhotoUploadUseCase: KoinComponent {

    private val uploadService by inject<UploadService>()

    suspend operator fun invoke(photo: BufferedImage, userId: String) : BaseResponse<PhotosEntity>{
        return uploadService.uploadPhoto(photo, userId)
    }

}