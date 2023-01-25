package com.github.bgrebennikov.common

import com.auth0.jwt.algorithms.Algorithm

const val SERVER_DOMAIN = "server.domain"

const val JWT_AUDIENCE = "jwt.audience"
const val JWT_REALM = "jwt.realm"
const val JWT_SECRET = "secret"
const val JWT_DOMAIN = "jwt.domain"

const val FIELD_USER_ID = "userId"
const val FIELD_EMAIL = "email"


const val CLAIM_USER_ID = "userId"
const val CLAIM_EMAIL = "email"

const val HASHING_SALT_PROPERTY = "hashing.salt"

val JWT_ALGORITHM: Algorithm = Algorithm.HMAC256(JWT_SECRET)


const val PHOTO_UPLOAD_MAX_SIZE = 1600

val thumbnailSizes = mapOf<String, Int>(
    "photo_50" to 50,
    "photo_100" to 100,
    "photo_150" to 150,
    "photo_200" to 200,
)

const val UPLOADS_PATH_ROOT = "uploads"

