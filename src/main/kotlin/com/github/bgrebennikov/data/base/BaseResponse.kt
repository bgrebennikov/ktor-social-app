package com.github.bgrebennikov.data.base

import io.ktor.http.*


data class BaseResponse<T>(
    val status: HttpStatusCode = HttpStatusCode.OK,
    val response: T? = null,
    val errors : List<String>?
){
    fun hasErrors(callback: (List<String>) -> Unit){
        this.errors?.let{ callback.invoke(it) }
    }
}