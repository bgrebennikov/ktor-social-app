package com.github.bgrebennikov.usecases.search

import com.github.bgrebennikov.common.Errors
import com.github.bgrebennikov.data.base.BaseResponse
import com.github.bgrebennikov.data.entity.prompts.PlacesSearchEntity
import com.github.bgrebennikov.services.search.SearchService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchPlacesUseCase : KoinComponent {

    private val searchService : SearchService by inject()

    suspend operator fun invoke(query: String?) : BaseResponse<List<PlacesSearchEntity>>{
        query ?: return Errors.Search.EMPTY_PLACES_SEARCH_QUERY

        return BaseResponse(
            response = searchService.searchPlaces(query)
        )

    }

}