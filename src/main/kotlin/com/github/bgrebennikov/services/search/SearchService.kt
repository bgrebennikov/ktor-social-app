package com.github.bgrebennikov.services.search

import com.github.bgrebennikov.data.entity.prompts.HobbiesSearchEntity
import com.github.bgrebennikov.data.entity.prompts.PlacesSearchEntity

interface SearchService {

    suspend fun searchHobbies(hobbyTitle: String) : List<HobbiesSearchEntity>

    suspend fun searchPlaces(placeTitle: String): List<PlacesSearchEntity>

    suspend fun getPlaceById(placeId: String?) : PlacesSearchEntity?

    suspend fun getHobbiesByIds(ids: List<String>) : List<HobbiesSearchEntity>

}