package com.github.bgrebennikov.data.requests.auth

data class SignupRequestDto(
    val username: String,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String
)
