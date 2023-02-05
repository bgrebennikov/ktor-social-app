package com.github.bgrebennikov.data.jwt

import io.ktor.server.auth.*
import java.util.*

data class UserJwtPrincipal(
    val userId: String,
    val email: String,
    val expireAt: Date
) : Principal