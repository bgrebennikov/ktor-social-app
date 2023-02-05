package com.github.bgrebennikov.DI.modules

import com.github.bgrebennikov.datasource.SearchDataSource
import com.github.bgrebennikov.datasource.SearchDataSourceImpl
import com.github.bgrebennikov.services.search.SearchService
import com.github.bgrebennikov.services.search.SearchServiceImpl
import org.koin.dsl.module

val searchModule = module {
    single<SearchService> {
        SearchServiceImpl()
    }

    single<SearchDataSource> {
        SearchDataSourceImpl()
    }
}