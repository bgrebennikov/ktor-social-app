package com.github.bgrebennikov.DI.modules

import com.github.bgrebennikov.datasource.UserDataSource
import com.github.bgrebennikov.datasource.UserDataSourceImpl
import org.koin.dsl.module

val userDataSourceModule = module {
    single<UserDataSource> {
        UserDataSourceImpl()
    }
}