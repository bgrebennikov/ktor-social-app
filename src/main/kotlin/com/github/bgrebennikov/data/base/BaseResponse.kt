package com.github.bgrebennikov.data.base

import io.ktor.http.*


data class BaseResponse<T>(
    val status: HttpStatusCode = HttpStatusCode.OK,
    val response: T? = null,
    val errors : List<ResponseError>? = null
){
    fun hasErrors(callback: (List<ResponseError>) -> Unit){
        this.errors?.let{ callback.invoke(it) }
    }
}