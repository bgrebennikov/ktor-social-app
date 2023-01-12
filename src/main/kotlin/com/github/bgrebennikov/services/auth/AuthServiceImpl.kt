package com.github.bgrebennikov.services.auth

import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.base.ErrorType
import com.github.bgrebennikov.data.base.ResponseError
import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.requests.auth.SignupRequestDto
import com.github.bgrebennikov.datasource.UserDataSource
import com.github.bgrebennikov.services.auth.jwt.JwtService
import io.ktor.http.*
import org.bson.types.ObjectId
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.math.sign

class AuthServiceImpl : AuthService, KoinComponent {

    private val userDataSource by inject<UserDataSource>()
    private val jwtService by inject<JwtService>()

    private suspend fun userExists(email: String): Boolean {
        return userDataSource.findUserByEmail(email) != null
    }

    override suspend fun signUp(signupRequest: SignupRequestDto): BaseResponse<UserEntity> {

        if (!userExists(signupRequest.email)) return BaseResponse(
            status = HttpStatusCode.BadRequest,
            errors = listOf(
                ResponseError(
                    message = "User already exists",
                    field = "email",
                    type = ErrorType.EMAIL_ALREADY_EXISTS
                )
            )
        )

        val generatedId = ObjectId().toString()


        return BaseResponse(
            response = userDataSource.insertUser(
                UserEntity(
                    id = generatedId,
                    token = jwtService.generateToken(signupRequest, generatedId),
                    passwordHash = signupRequest.password,
                    profile = UserEntity.UserProfile(
                        id = generatedId,
                        firstName = signupRequest.firstName,
                        email = signupRequest.email
                    )
                )
            )
        )

    }
}