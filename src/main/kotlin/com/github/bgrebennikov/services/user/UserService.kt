package com.github.bgrebennikov.services.user

import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.responses.user.edit.EditAboutResponse
import com.github.bgrebennikov.data.responses.user.edit.EditAvatarResponse
import com.github.bgrebennikov.data.responses.user.edit.EditHobbiesResponse

interface UserService {

    suspend fun init(userId: String?) : BaseResponse<UserEntity>
    suspend fun updateAvatar(userId: String, photoId: String?) : BaseResponse<EditAvatarResponse>

    suspend fun updateAbout(userId: String, about : UserEntity.About) : BaseResponse<EditAboutResponse>

    suspend fun updateHobbies(userId: String, hobbies: List<UserEntity.Hobbies>): BaseResponse<EditHobbiesResponse>
}