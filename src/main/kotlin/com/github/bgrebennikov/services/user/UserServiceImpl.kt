package com.github.bgrebennikov.services.user

import com.github.bgrebennikov.common.Errors
import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.requests.user.edit.EditAvatarAction
import com.github.bgrebennikov.data.requests.user.edit.EditAvatarResponse
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

        if (updateResult){
            return BaseResponse(
                response = EditAvatarResponse(
                    EditAvatarAction.UPDATE, source.src
                )
            )
        }

        return Errors.Server.INTERNAL_ERROR
    }


}