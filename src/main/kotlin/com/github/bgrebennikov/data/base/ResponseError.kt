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
    REFRESH_TOKEN_EXPIRED,
    INVALID_AUTHOR_ID,
    WRONG_CREDENTIALS,
    INVALID_REFRESH_TOKEN,
    INVALID_SESSION,
    USER_NOT_FOUND,
    UNRESOLVED_ACTION,

    WRONG_UPLOAD_PHOTO_ID,
    EMPTY_SEARCH_QUERY,
    INTERNAL_SERVER_ERROR
}