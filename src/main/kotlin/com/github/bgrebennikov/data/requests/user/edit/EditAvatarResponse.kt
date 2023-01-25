package com.github.bgrebennikov.data.requests.user.edit

data class EditAvatarResponse(
    val action: EditAvatarAction,
    val source: String? = null,
)