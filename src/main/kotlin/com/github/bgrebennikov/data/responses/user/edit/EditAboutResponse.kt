package com.github.bgrebennikov.data.responses.user.edit

import com.github.bgrebennikov.data.requests.user.UserActions

data class EditAboutResponse(
    val action: UserActions,
    val isSuccessful : Boolean
)
