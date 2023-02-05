package com.github.bgrebennikov.usecases.search

import com.github.bgrebennikov.common.Errors
import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.entity.prompts.HobbiesSearchEntity
import com.github.bgrebennikov.services.search.SearchService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchHobbyUseCase() : KoinComponent {

    private val searchService by inject<SearchService>()

    suspend operator fun invoke(hobbyTitle: String?) : BaseResponse<List<HobbiesSearchEntity>>{

        hobbyTitle ?: return Errors.Search.EMPTY_SEARCH_QUERY

        return BaseResponse(
            response = searchService.searchHobbies(hobbyTitle)
        )
    }

}