package com.github.bgrebennikov.data.uploads


enum class UploadPhotoAlbum {
    DOCUMENT, WALL, SAVED
}

sealed class PhotoUploadType(
    val album : UploadPhotoAlbum = UploadPhotoAlbum.DOCUMENT
)