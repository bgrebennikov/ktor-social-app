package com.github.bgrebennikov.services.user

import com.github.bgrebennikov.common.Errors
import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.requests.user.UserActions
import com.github.bgrebennikov.data.responses.user.edit.EditAboutResponse
import com.github.bgrebennikov.data.responses.user.edit.EditAvatarResponse
import com.github.bgrebennikov.data.responses.user.edit.EditHobbiesResponse
import com.github.bgrebennikov.data.uploads.PhotosEntity
import com.github.bgrebennikov.datasource.UploadsDataSource
import com.github.bgrebennikov.datasource.UserDataSource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserServiceImpl : UserService, KoinComponent {

    private val userDataSource by inject<UserDataSource>()
    private val uploadsDataSource by inject<UploadsDataSource>()

    override suspend fun init(userId: String?): BaseResponse<UserEntity> {
        val user = userDataSource.findUserById(userId.toString()) ?: return Errors.User.USER_NOT_FOUND

        return BaseResponse(
            response = user
        )
    }

    override suspend fun updateAvatar(userId: String, photoId: String?): BaseResponse<EditAvatarResponse> {
        photoId ?: return Errors.Uploads.WRONG_UPLOAD_PHOTO_ID

        val source = uploadsDataSource.editParams(photoId, PhotosEntity.Params(isAvatar = true))
            ?: return Errors.Uploads.WRONG_UPLOAD_PHOTO_ID

        val updateResult = userDataSource.updateUserAvatar(userId, source)

        if (updateResult) {
            return BaseResponse(
                response = EditAvatarResponse(
                    UserActions.UPDATE, source.src
                )
            )
        }

        return Errors.Server.INTERNAL_ERROR
    }

    override suspend fun updateAbout(userId: String, about: UserEntity.About): BaseResponse<EditAboutResponse> {
        val updateResult = userDataSource.updateAbout(userId, about)
        return BaseResponse(
            response = EditAboutResponse(
                UserActions.UPDATE,
                updateResult.wasAcknowledged(),
            )
        )
    }

    override suspend fun updateHobbies(
        userId: String,
        hobbies: List<UserEntity.Hobbies>
    ): BaseResponse<EditHobbiesResponse> {

        userDataSource.updateUserHobby(userId, hobbies)

        return BaseResponse(
            response = EditHobbiesResponse(
                UserActions.UPDATE,
                userDataSource.getUserHobbies(userId)
            )
        )

    }


}