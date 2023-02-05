package com.github.bgrebennikov.data.responses.user.edit

import com.github.bgrebennikov.data.requests.user.UserActions

data class EditAvatarResponse(
    val action: UserActions,
    val source: String? = null,
)