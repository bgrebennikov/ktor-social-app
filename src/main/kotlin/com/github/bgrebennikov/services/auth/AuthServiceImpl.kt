package com.github.bgrebennikov.services.auth

import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.requests.auth.SignupRequestDto
import com.github.bgrebennikov.datasource.UserDataSource
import org.bson.types.ObjectId
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AuthServiceImpl : AuthService, KoinComponent {

    private val userDataSource by inject<UserDataSource>()

    override suspend fun signUp(signupRequest: SignupRequestDto): UserEntity {

        val generatedId = ObjectId().toString()

        return userDataSource.insertUser(
            UserEntity(
                id = generatedId,
                token = "fsefsefsef",
                passwordHash = signupRequest.password,
                profile = UserEntity.UserProfile(
                    id = generatedId,
                    firstName = signupRequest.firstName
                )
            )
        )

    }
}