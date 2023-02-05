package com.github.bgrebennikov.usecases.user.edit

import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.mappers.mapTo
import com.github.bgrebennikov.data.requests.user.UserActions
import com.github.bgrebennikov.data.requests.user.edit.EditHobbiesRequest
import com.github.bgrebennikov.data.responses.user.edit.EditHobbiesResponse
import com.github.bgrebennikov.services.search.SearchService
import com.github.bgrebennikov.services.user.UserService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EditHobbiesUseCase : KoinComponent {

    private val userService: UserService by inject()
    private val searchService: SearchService by inject()

    suspend operator fun invoke(userId: String, request: EditHobbiesRequest): BaseResponse<EditHobbiesResponse> {
        return when (request.action) {
            UserActions.UPDATE -> {
                println(request.hobbiesIds)
                val hobbiesList = searchService.getHobbiesByIds(request.hobbiesIds)
                return userService.updateHobbies(userId, hobbiesList.mapTo(UserEntity.Hobbies::class))
            }

            else -> {
                BaseResponse(
                    response = null
                )
            }
        }
    }


}