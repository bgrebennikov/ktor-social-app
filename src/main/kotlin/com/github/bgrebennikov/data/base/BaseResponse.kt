package com.github.bgrebennikov.data.base


data class BaseResponse<T>(
    val response: T? = null,
    val errors : List<ResponseError>? = listOf()
){
    fun hasErrors(callback: (List<ResponseError>) -> Unit){
        this.errors?.let{ callback.invoke(it) }
    }
}