package com.github.bgrebennikov.datasource

import com.github.bgrebennikov.data.entity.prompts.HobbiesSearchEntity
import com.github.bgrebennikov.data.entity.prompts.PlacesSearchEntity

interface SearchDataSource {

    suspend fun searchHobbiesByTitle(hobbyTitle : String) : List<HobbiesSearchEntity>

    suspend fun searchPlaceByTitle(placeTitle: String) : List<PlacesSearchEntity>

    suspend fun getPlaceById(placeId: String) : PlacesSearchEntity?

    suspend fun getHobbiesByIds(ids: List<String>) : List<HobbiesSearchEntity>

}