package com.github.bgrebennikov.DI.modules

import com.github.bgrebennikov.datasource.UploadsDataSource
import com.github.bgrebennikov.datasource.UploadsDataSourceImpl
import com.github.bgrebennikov.services.photos.UploadService
import com.github.bgrebennikov.services.photos.UploadServiceImpl
import org.koin.dsl.module

val uploadModule = module {
    single<UploadService> {
        UploadServiceImpl()
    }
    single<UploadsDataSource> {
        UploadsDataSourceImpl()
    }
}