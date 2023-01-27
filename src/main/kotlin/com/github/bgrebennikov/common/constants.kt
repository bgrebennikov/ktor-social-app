package com.github.bgrebennikov.common

import com.auth0.jwt.algorithms.Algorithm
import java.util.*

const val SERVER_DOMAIN = "server.domain"

const val JWT_AUDIENCE = "jwt.audience"
const val JWT_REALM = "jwt.realm"
const val JWT_SECRET = "secret"
const val JWT_DOMAIN = "jwt.domain"

val ONE_DAY = 3_600_000 * 24
val ONE_MONTH = Calendar.getInstance().apply {
    add(Calendar.MONTH, 1)
}.time.time

val ACCESS_TOKEN_EXPIRE_DATE = Date(System.currentTimeMillis() + ONE_DAY)
val REFRESH_TOKEN_EXPIRE_DATE = Date(System.currentTimeMillis() + ONE_MONTH)

const val FIELD_USER_ID = "userId"
const val FIELD_EMAIL = "email"


const val CLAIM_USER_ID = "userId"
const val CLAIM_EMAIL = "email"

const val HASHING_SALT_PROPERTY = "hashing.salt"
const val REDIS_HOST_PROPERTY = "redis.host"
const val REDIS_PORT_PROPERTY = "redis.port"

val JWT_ALGORITHM: Algorithm = Algorithm.HMAC256(JWT_SECRET)


const val PHOTO_UPLOAD_MAX_SIZE = 1600

val thumbnailSizes = mapOf<String, Int>(
    "photo_50" to 50,
    "photo_100" to 100,
    "photo_150" to 150,
    "photo_200" to 200,
)

const val UPLOADS_PATH_ROOT = "uploads"

