package com.github.bgrebennikov.data.requests.auth

import io.ktor.server.auth.*

data class UserJwtPrincipal(
    val userId: String,
    val email: String
) : Principal