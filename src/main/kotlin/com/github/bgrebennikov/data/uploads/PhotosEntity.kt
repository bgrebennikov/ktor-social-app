package com.github.bgrebennikov.data.uploads

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class PhotosEntity(
    @BsonId
    val id: String = ObjectId().toString(),
    val ownerId: String,
    val path: String,
    val src: String,
    val uploadDate: Long = System.currentTimeMillis(),
    val thumbnails: Map<String, String>,
    val params: Params = Params()
) : PhotoUploadType(){
    data class Params(
        val isAvatar: Boolean = false,
        val lastUpdate : Long = System.currentTimeMillis()
    )
}
