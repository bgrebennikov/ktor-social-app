package com.github.bgrebennikov.usecases.user.edit

import com.github.bgrebennikov.common.Errors
import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.base.ErrorType
import com.github.bgrebennikov.data.base.ResponseError
import com.github.bgrebennikov.data.requests.user.UserActions
import com.github.bgrebennikov.data.requests.user.edit.EditAvatarRequest
import com.github.bgrebennikov.data.responses.user.edit.EditAvatarResponse
import com.github.bgrebennikov.services.user.UserService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EditAvatarUseCase : KoinComponent {

    private val userService by inject<UserService>()

    suspend operator fun invoke(request: EditAvatarRequest, userId: String?): BaseResponse<EditAvatarResponse> {
        userId ?: return BaseResponse(
            response = EditAvatarResponse(UserActions.UPDATE),
            errors = listOf(
                ResponseError("Something went wrong...", type = ErrorType.INTERNAL_SERVER_ERROR)
            )
        )
        return when (request.action) {
            UserActions.UPDATE -> userService.updateAvatar(userId, request.photoId)
            UserActions.REMOVE -> BaseResponse(
                response = EditAvatarResponse(UserActions.REMOVE)
            )

            else -> Errors.User.UNRESOLVED_USER_ACTION
        }
    }

}