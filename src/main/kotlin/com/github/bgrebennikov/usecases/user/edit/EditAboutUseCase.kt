package com.github.bgrebennikov.usecases.user.edit

import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.entity.user.UserEntity
import com.github.bgrebennikov.data.mappers.mapTo
import com.github.bgrebennikov.data.requests.user.edit.EditAboutRequest
import com.github.bgrebennikov.data.responses.user.edit.EditAboutResponse
import com.github.bgrebennikov.services.search.SearchService
import com.github.bgrebennikov.services.user.UserService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EditAboutUseCase : KoinComponent {

    private val userService: UserService by inject()
    private val searchService: SearchService by inject()

    suspend operator fun invoke(userId: String, request: EditAboutRequest): BaseResponse<EditAboutResponse> {

        val place = searchService.getPlaceById(request.about.place?.id)

        return userService.updateAbout(
            userId,
            request.about.copy(
                place = place?.mapTo(UserEntity.About.Place::class)
            )
        )

    }

}