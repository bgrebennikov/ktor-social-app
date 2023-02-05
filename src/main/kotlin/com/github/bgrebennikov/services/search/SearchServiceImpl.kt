package com.github.bgrebennikov.services.search

import com.github.bgrebennikov.data.entity.prompts.HobbiesSearchEntity
import com.github.bgrebennikov.data.entity.prompts.PlacesSearchEntity
import com.github.bgrebennikov.datasource.SearchDataSource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchServiceImpl : SearchService, KoinComponent {

    private val searchDataSource by inject<SearchDataSource>()

    override suspend fun searchHobbies(hobbyTitle: String): List<HobbiesSearchEntity> {
        return searchDataSource.searchHobbiesByTitle(hobbyTitle)
    }

    override suspend fun searchPlaces(placeTitle: String): List<PlacesSearchEntity> {
        return searchDataSource.searchPlaceByTitle(placeTitle)
    }

    override suspend fun getPlaceById(placeId: String?): PlacesSearchEntity? {
        placeId ?: return null
        return searchDataSource.getPlaceById(placeId)
    }

    override suspend fun getHobbiesByIds(ids: List<String>): List<HobbiesSearchEntity> {
        return searchDataSource.getHobbiesByIds(ids)
    }
}