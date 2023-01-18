package com.github.bgrebennikov.data.base

data class ResponseError(
    val message: String,
    val field: String? = null,
    val type: ErrorType
)

enum class ErrorType{
    INCORRECT_EMAIL, MISSING_EMAIL, EMAIL_ALREADY_EXISTS,
    INCORRECT_PASSWORD, INVALID_CREDENTIALS,
    INVALID_INPUT_LENGTH,
    TOKEN_EXPIRED,
    INVALID_AUTHOR_ID,
    WRONG_CREDENTIALS
}