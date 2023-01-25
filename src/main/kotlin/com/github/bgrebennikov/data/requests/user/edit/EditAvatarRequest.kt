package com.github.bgrebennikov.data.requests.user.edit

enum class EditAvatarAction{
    UPDATE, REMOVE
}

data class EditAvatarRequest(
    val action: EditAvatarAction,
    val photoId: String?
)