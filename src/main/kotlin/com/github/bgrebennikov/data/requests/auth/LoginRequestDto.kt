package com.github.bgrebennikov.data.requests.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    val login: String,
    val password: String
)
