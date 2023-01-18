package com.github.bgrebennikov.common

import com.auth0.jwt.algorithms.Algorithm

const val JWT_AUDIENCE = "jwt.audience"
const val JWT_REALM = "jwt.realm"
const val JWT_SECRET = "edrica_azaza"
const val JWT_DOMAIN = "jwt.domain"

const val FIELD_USER_ID = "userId"
const val FIELD_EMAIL = "email"


const val CLAIM_USER_ID = "userId"
const val CLAIM_EMAIL = "email"

const val HASHING_SALT_PROPERTY = "hashing.salt"

val JWT_ALGORITHM: Algorithm = Algorithm.HMAC256(JWT_SECRET)