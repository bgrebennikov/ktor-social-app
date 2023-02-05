package com.github.bgrebennikov.data.requests.user.edit

import com.github.bgrebennikov.data.requests.user.UserActions

data class EditAvatarRequest(
    val action: UserActions,
    val photoId: String?
)