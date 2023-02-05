package com.github.bgrebennikov.data.auth

data class AuthResponse(
    val id: String,
    val accessToken: String,
    val refreshToken: String,
    val lastUpdate: Long = System.currentTimeMillis()
)