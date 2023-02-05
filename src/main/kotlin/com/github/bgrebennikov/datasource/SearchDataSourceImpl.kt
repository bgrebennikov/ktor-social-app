package com.github.bgrebennikov.datasource

import com.github.bgrebennikov.data.entity.prompts.HobbiesSearchEntity
import com.github.bgrebennikov.data.entity.prompts.PlacesSearchEntity
import com.mongodb.client.model.Aggregates.search
import com.mongodb.client.model.Aggregates.sort
import com.mongodb.client.model.Filters
import com.mongodb.client.model.search.SearchOperator.autocomplete
import com.mongodb.client.model.search.SearchOperator.compound
import com.mongodb.client.model.search.SearchOptions
import com.mongodb.client.model.search.SearchPath.fieldPath
import org.bson.types.ObjectId
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.litote.kmongo.ascendingIndex
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.aggregate
import org.litote.kmongo.limit

class SearchDataSourceImpl : SearchDataSource, KoinComponent {

    private val database by inject<CoroutineDatabase>()

    private val hobbies = database.getCollection<HobbiesSearchEntity>()
    private val places = database.getCollection<PlacesSearchEntity>()


    override suspend fun searchHobbiesByTitle(hobbyTitle: String): List<HobbiesSearchEntity> {
        return hobbies.aggregate<HobbiesSearchEntity>(
            search(
                compound().should(
                    listOf(
                        autocomplete(
                            fieldPath("title"),
                            hobbyTitle
                        )
                    )
                ),
                SearchOptions.searchOptions().index("title")
            )
        ).toList()
    }

    override suspend fun searchPlaceByTitle(placeTitle: String): List<PlacesSearchEntity> {

        return places.aggregate<PlacesSearchEntity>(
            search(
                compound()
                    .should(
                        listOf(
                            autocomplete(
                                fieldPath("city_title"),
                                placeTitle
                            )
                        )
                    ),
                SearchOptions.searchOptions().index("city_title")
            ),
            limit(10),
            sort(
                ascendingIndex(PlacesSearchEntity::city_title)
            )

        ).toList()
    }

    override suspend fun getPlaceById(placeId: String): PlacesSearchEntity? {
        return places.findOneById(ObjectId(placeId))
    }

    override suspend fun getHobbiesByIds(ids: List<String>): List<HobbiesSearchEntity> {


        return hobbies.find(Filters.`in`(
            "_id", ids.map { ObjectId(it) }
        )).toList()

    }
}