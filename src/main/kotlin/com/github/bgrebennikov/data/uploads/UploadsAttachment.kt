package com.github.bgrebennikov.data.uploads

enum class AttachmentType{
    PHOTO, DOCUMENT, VOICE
}

sealed class UploadsAttachment(
    val type: AttachmentType
)