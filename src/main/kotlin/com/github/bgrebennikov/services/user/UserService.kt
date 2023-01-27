package com.github.bgrebennikov.services.user

import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.responses.user.edit.EditAvatarResponse

interface UserService {

    suspend fun init(userId: String?) : BaseResponse<UserEntity>
    suspend fun updateAvatar(userId: String, photoId: String?) : BaseResponse<EditAvatarResponse>

}