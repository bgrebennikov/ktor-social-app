package com.github.bgrebennikov.data.responses.auth

import com.github.bgrebennikov.data.requests.user.UserActions

data class LogoutResponse(
    val action: UserActions,
    val status: String? = null
)