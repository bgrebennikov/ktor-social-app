package com.github.bgrebennikov.common

import com.github.bgrebennikov.data.auth.AuthResponse
import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.base.ErrorType
import com.github.bgrebennikov.data.base.ResponseError
import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.requests.user.UserActions
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