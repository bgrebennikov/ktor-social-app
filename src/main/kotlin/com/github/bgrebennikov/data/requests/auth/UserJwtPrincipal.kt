package com.github.bgrebennikov.data.requests.auth

data class UserJwtPrincipal(
    val userId: String,
    val email: String
)
