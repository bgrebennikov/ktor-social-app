package com.github.bgrebennikov.common

import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.base.ErrorType
import com.github.bgrebennikov.data.base.ResponseError
import com.github.bgrebennikov.data.entity.prompts.HobbiesSearchEntity
import com.github.bgrebennikov.data.entity.prompts.PlacesSearchEntity
import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.requests.user.UserActions
import com.github.bgrebennikov.data.responses.auth.AuthResponse
import com.github.bgrebennikov.data.responses.user.edit.EditAvatarResponse

object Errors {
    object User {
        val USER_ALREADY_EXISTS = BaseResponse<AuthResponse>(
            errors = listOf(
                ResponseError(
                    message = "User already exists",
                    field = "email",
                    type = ErrorType.EMAIL_ALREADY_EXISTS
                )
            )
        )

        val USER_NOT_FOUND = BaseResponse<UserEntity>(
            errors = listOf(
                ResponseError(
                    message = "USER_NOT_FOUND",
                    type = ErrorType.USER_NOT_FOUND
                )
            )
        )

        val UNRESOLVED_USER_ACTION = BaseResponse<EditAvatarResponse>(
            errors = listOf(
                ResponseError(
                    message = "UNRESOLVED_USER_ACTION",
                    type = ErrorType.UNRESOLVED_ACTION
                )
            )
        )

        object Avatar{
            val INVALID_USER_ID = BaseResponse(
                response = EditAvatarResponse(UserActions.UPDATE),
                errors = listOf(
                    ResponseError("Invalid user Id", type = ErrorType.INTERNAL_SERVER_ERROR)
                )
            )
        }

    }

    object Uploads {
        val WRONG_UPLOAD_PHOTO_ID = BaseResponse(
            response = EditAvatarResponse(action = UserActions.UPDATE),
            errors = listOf(
                ResponseError(
                    message = "File upload id must be not empty",
                    type = ErrorType.WRONG_UPLOAD_PHOTO_ID
                )
            )
        )
    }

    object Auth {
        val LOGIN_WRONG_CREDENTIALS = BaseResponse<AuthResponse>(
            errors = listOf(
                ResponseError(
                    message = "Wrong credentials. Check your login or password",
                    type = ErrorType.WRONG_CREDENTIALS
                )
            )
        )
        val TOKEN_EXPIRED = BaseResponse(
            response = null,
            errors = listOf(
                ResponseError(
                    message = "Token is not valid or has been expired",
                    type = ErrorType.TOKEN_EXPIRED
                )
            )
        )
        val REFRESH_TOKEN_EXPIRED = BaseResponse<AuthResponse>(
            response = null,
            errors = listOf(
                ResponseError(
                    message = "This refresh token was expired",
                    type = ErrorType.TOKEN_EXPIRED
                )
            )
        )
        val INVALID_SESSION = BaseResponse<AuthResponse>(
            errors = listOf(
                ResponseError(
                    message = "Invalid session. Try login again",
                    type = ErrorType.INVALID_SESSION
                )
            )
        )
        val INVALID_REFRESH_TOKEN = BaseResponse<AuthResponse>(
            errors = listOf(
                ResponseError(
                    message = "Invalid refresh token",
                    type = ErrorType.INVALID_REFRESH_TOKEN
                )
            )
        )
        val REFRESH_TOKEN_VALIDATION_ERROR = BaseResponse<AuthResponse>(
            errors = listOf(
                ResponseError(
                    message = "Error validate refresh token",
                    type = ErrorType.INVALID_REFRESH_TOKEN
                )
            )
        )
    }

    object Search{
        val EMPTY_SEARCH_QUERY = BaseResponse<List<HobbiesSearchEntity>>(
            errors = listOf(
                ResponseError(
                    message = "search query must be not empty",
                    type = ErrorType.EMPTY_SEARCH_QUERY
                )
            )
        )
        val EMPTY_PLACES_SEARCH_QUERY = BaseResponse<List<PlacesSearchEntity>>(
            errors = listOf(
                ResponseError(
                    message = "search query must be not empty",
                    type = ErrorType.EMPTY_SEARCH_QUERY
                )
            )
        )
    }

    object Server {
        val INTERNAL_ERROR = BaseResponse(
            response = EditAvatarResponse(
                UserActions.UPDATE
            ),
            errors = listOf(
                ResponseError(
                    message = "Something went wrong...",
                    type = ErrorType.INTERNAL_SERVER_ERROR
                )
            )
        )
    }

}