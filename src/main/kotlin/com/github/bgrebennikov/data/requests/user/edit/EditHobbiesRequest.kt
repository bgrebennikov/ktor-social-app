package com.github.bgrebennikov.data.requests.user.edit

import com.github.bgrebennikov.data.requests.user.UserActions

data class EditHobbiesRequest(
    val action: UserActions,
    val hobbiesIds: List<String>
)
