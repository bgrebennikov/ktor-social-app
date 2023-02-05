package com.github.bgrebennikov.routes.search

import com.github.bgrebennikov.usecases.search.SearchHobbyUseCase
import com.github.bgrebennikov.usecases.search.SearchPlacesUseCase
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.searchRoute(){
    route("/search"){
        route("/hobbies/"){
            searchHobbies()
        }
        route("/places"){
            searchPlaces()
        }
    }
}

private fun Route.searchPlaces(){
    post("{query}"){
        val query = call.parameters["query"]
        call.respond(
            SearchPlacesUseCase().invoke(query)
        )
    }
}


private fun Route.searchHobbies(){
    post("{query}") {
        val query = call.parameters["query"]
        call.respond(SearchHobbyUseCase().invoke(query))
    }
}