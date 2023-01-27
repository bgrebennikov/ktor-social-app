package com.github.bgrebennikov.datasource

interface RedisDataSource {

    fun set(key: String, value: String, expireIn: Long? = null) : String
    fun get(key: String) : String?

}