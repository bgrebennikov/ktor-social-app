package com.github.bgrebennikov.DI.modules

import com.github.bgrebennikov.common.REDIS_HOST_PROPERTY
import com.github.bgrebennikov.common.REDIS_PORT_PROPERTY
import com.github.bgrebennikov.datasource.RedisDataSource
import com.github.bgrebennikov.datasource.RedisDataSourceImpl
import io.ktor.server.application.*
import org.koin.dsl.module
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool

val redisModule = module {
    single<Jedis> {
        val application : Application = get()

        val host = application.environment.config.property(REDIS_HOST_PROPERTY).getString()
        val port = application.environment.config.property(REDIS_PORT_PROPERTY).getString()

        JedisPool(host, port.toInt()).resource
    }

    single<RedisDataSource>{
        RedisDataSourceImpl()
    }
}