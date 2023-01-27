package com.github.bgrebennikov.data.jwt

import io.ktor.server.auth.*

data class UserJwtPrincipal(
    val userId: String,
    val email: String
) : Principal