package com.github.bgrebennikov.common

import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.base.ErrorType
import com.github.bgrebennikov.data.base.ResponseError
import com.github.bgrebennikov.data.entity.user.UserEntity
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

}