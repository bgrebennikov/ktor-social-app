package com.github.bgrebennikov.DI.modules

import com.github.bgrebennikov.datasource.UserDataSource
import com.github.bgrebennikov.datasource.UserDataSourceImpl
import com.github.bgrebennikov.services.user.UserService
import com.github.bgrebennikov.services.user.UserServiceImpl
import org.koin.dsl.module

val userModule = module {
    single<UserDataSource> {
        UserDataSourceImpl()
    }

    single<UserService>{
        UserServiceImpl()
    }
}