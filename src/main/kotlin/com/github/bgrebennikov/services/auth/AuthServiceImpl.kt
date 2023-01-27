package com.github.bgrebennikov.services.auth

import com.github.bgrebennikov.common.Errors
import com.github.bgrebennikov.common.ONE_DAY
import com.github.bgrebennikov.data.auth.AuthResponse
import com.github.bgrebennikov.data.auth.SettingsEntity
import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.requests.auth.LoginRequestDto
import com.github.bgrebennikov.data.requests.auth.SignupRequestDto
import com.github.bgrebennikov.data.requests.user.UserActions
import com.github.bgrebennikov.data.responses.auth.LogoutResponse
import com.github.bgrebennikov.datasource.AuthDataSource
import com.github.bgrebennikov.datasource.RedisDataSource
import com.github.bgrebennikov.datasource.UserDataSource
import com.github.bgrebennikov.services.security.hashing.HashingService
import com.github.bgrebennikov.services.security.jwt.JwtService
import org.bson.types.ObjectId
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.TimeUnit

class AuthServiceImpl : AuthService, KoinComponent {

    private val userDataSource by inject<UserDataSource>()
    private val authDataSource by inject<AuthDataSource>()
    private val hashingService by inject<HashingService>()
    private val jwtService by inject<JwtService>()

    private val redisDataSource by inject<RedisDataSource>()

    private suspend fun userExists(email: String): Boolean {
        return userDataSource.findUserByEmail(email) != null
    }

    override suspend fun signUp(signupRequest: SignupRequestDto): BaseResponse<AuthResponse> {

        if (userExists(signupRequest.email)) return Errors.User.USER_ALREADY_EXISTS
        val generatedId = ObjectId().toString()

        userDataSource.insertUser(
            UserEntity(
                id = generatedId,
                profile = UserEntity.UserProfile(
                    id = generatedId,
                    firstName = signupRequest.firstName,
                    email = signupRequest.email
                ),
            )
        )

        authDataSource.insertSettings(
            SettingsEntity(
                id = generatedId,
                passwordHash = hashingService.generateSaltedHash(signupRequest.password),
            )
        )

        return BaseResponse(
            response = AuthResponse(
                id = generatedId,
                accessToken = jwtService.generateAccessToken(signupRequest, generatedId),
                refreshToken = jwtService.generateRefreshToken(),
            )
        )
    }

    override suspend fun login(loginRequestDto: LoginRequestDto): BaseResponse<AuthResponse> {

        val userEntity = userDataSource.findUserByEmail(loginRequestDto.login)
            ?: return Errors.Auth.LOGIN_WRONG_CREDENTIALS

        val userSettings = authDataSource.findUserSettingsById(userEntity.id)
            ?: return Errors.Auth.LOGIN_WRONG_CREDENTIALS

        val isValidCredentials = hashingService.verify(
            loginRequestDto.password, userSettings.passwordHash
        )

        if (!isValidCredentials) return Errors.Auth.LOGIN_WRONG_CREDENTIALS

        return BaseResponse(
            response = AuthResponse(
                userEntity.id,
                accessToken = jwtService.generateAccessToken(
                    loginRequestDto,
                    userEntity.id
                ),
                refreshToken = jwtService.generateRefreshToken()
            )
        )

    }

    override suspend fun logout(userId: String, token: String): BaseResponse<LogoutResponse> {
        val deny = redisDataSource.set(token, userId, TimeUnit.MILLISECONDS.toSeconds(ONE_DAY.toLong()))
        return BaseResponse(
            response = LogoutResponse(
                UserActions.LOGOUT,
                status = deny
            )
        )
    }

    override suspend fun tokenIsDenny(token: String?): Boolean {
        token ?: return false
        return redisDataSource.get(token) != null
    }


}