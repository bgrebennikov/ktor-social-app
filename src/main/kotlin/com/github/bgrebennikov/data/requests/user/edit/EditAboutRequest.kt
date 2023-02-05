package com.github.bgrebennikov.data.requests.user.edit

import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.requests.user.UserActions

data class EditAboutRequest(
    val action: UserActions,
    val about: UserEntity.About
)
