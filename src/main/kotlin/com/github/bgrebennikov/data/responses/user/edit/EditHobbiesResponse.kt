package com.github.bgrebennikov.data.responses.user.edit

import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.requests.user.UserActions

data class EditHobbiesResponse(
    val action: UserActions,
    val updatedList: List<UserEntity.Hobbies>
)
