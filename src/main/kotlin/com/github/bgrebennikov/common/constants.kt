package com.github.bgrebennikov.common

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

const val SERVER_DOMAIN = "server.domain"

const val JWT_ACCESS_TOKEN_AUDIENCE = "jwt.audience"
const val JWT_ACCESS_TOKEN_REALM = "jwt.realm"
const val JWT_ACCESS_TOKEN_SECRET = "KJGifo8osfia"
const val JWT_REFRESH_TOKEN_SECRET = "dw89(*&F*tf8"
const val JWT_DOMAIN = "jwt.domain"
const val JWT_ISSUER = "jwt.issuer"
const val ACCESS_TOKEN = "access-token"
const val REFRESH_TOKEN = "refresh_token"

val ONE_DAY = 3_600_000 * 24
val ONE_MONTH = Calendar.getInstance().apply {
    add(Calendar.MONTH, 1)
}.time.time

const val FIELD_USER_ID = "userId"
const val FIELD_EMAIL = "email"


const val CLAIM_USER_ID = "userId"
const val CLAIM_EMAIL = "email"

const val HASHING_SALT_PROPERTY = "hashing.salt"
const val REDIS_HOST_PROPERTY = "redis.host"
const val REDIS_PORT_PROPERTY = "redis.port"

val JWT_ALGORITHM: Algorithm = Algorithm.HMAC256(JWT_ACCESS_TOKEN_SECRET)
val JWT_REFRESH_ALGORITHM: Algorithm = Algorithm.HMAC256(JWT_REFRESH_TOKEN_SECRET)


const val PHOTO_UPLOAD_MAX_SIZE = 1600

val thumbnailSizes = mapOf<String, Int>(
    "photo_50" to 50,
    "photo_100" to 100,
    "photo_150" to 150,
    "photo_200" to 200,
)

const val UPLOADS_PATH_ROOT = "uploads"