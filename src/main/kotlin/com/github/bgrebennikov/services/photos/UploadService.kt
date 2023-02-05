package com.github.bgrebennikov.services.photos

import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.uploads.PhotosEntity
import java.awt.image.BufferedImage

interface UploadService {

    suspend fun uploadPhoto(photo: BufferedImage, userId: String) : BaseResponse<PhotosEntity>

}