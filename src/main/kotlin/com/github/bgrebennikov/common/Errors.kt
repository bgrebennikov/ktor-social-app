package com.github.bgrebennikov.common

import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.base.ErrorType
import com.github.bgrebennikov.data.base.ResponseError
import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.requests.user.edit.EditAvatarAction
import com.github.bgrebennikov.data.requests.user.edit.EditAvatarResponse
import io.ktor.http.*

object Errors {
    object User {
        val USER_ALREADY_EXISTS = BaseResponse<UserEntity>(
            status = HttpStatusCode.BadRequest,
            errors = listOf(
                ResponseError(
                    message = "User already exists",
                    field = "email",
                    type = ErrorType.EMAIL_ALREADY_EXISTS
                )
            )
        )

        val USER_NOT_FOUND = BaseResponse<UserEntity>(
            status = HttpStatusCode.BadRequest,
            errors = listOf(
                ResponseError(
                    message = "USER_NOT_FOUND",
                    type = ErrorType.USER_NOT_FOUND
                )
            )
        )
    }

    object Uploads {
        val WRONG_UPLOAD_PHOTO_ID = BaseResponse(
            response = EditAvatarResponse(action = EditAvatarAction.UPDATE),
            errors = listOf(
                ResponseError(
                    message = "File upload id must be not empty",
                    type = ErrorType.WRONG_UPLOAD_PHOTO_ID
                )
            )
        )
    }

    object Auth {
        val LOGIN_WRONG_CREDENTIALS = BaseResponse<UserEntity>(
            status = HttpStatusCode.BadRequest,
            errors = listOf(
                ResponseError(
                    message = "Wrong credentials. Check your login or password",
                    type = ErrorType.WRONG_CREDENTIALS
                )
            )
        )
    }

    object Server{
        val INTERNAL_ERROR = BaseResponse(
            response = EditAvatarResponse(
                EditAvatarAction.UPDATE
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