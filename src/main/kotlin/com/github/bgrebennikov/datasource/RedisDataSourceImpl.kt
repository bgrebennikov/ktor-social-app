package com.github.bgrebennikov.datasource

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import redis.clients.jedis.Jedis

class RedisDataSourceImpl : RedisDataSource, KoinComponent {

    private val redisInstance by inject<Jedis>()

    override fun set(key: String, value: String, expireIn: Long?): String {
        return redisInstance.apply {
            set(key, value)
            expireIn?.let {
                expire(key, expireIn)
            }
        }.get(key)
    }

    override fun get(key: String): String? {
        return redisInstance.get(key)
    }
}