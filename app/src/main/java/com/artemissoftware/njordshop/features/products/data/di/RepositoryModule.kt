package com.artemissoftware.njordshop.features.products.data.di

import com.artemissoftware.njordshop.core.database.dao.ProductDao
import com.artemissoftware.njordshop.core.network.source.DummyjsonApiSource
import com.artemissoftware.njordshop.features.products.data.repository.ProductsRepositoryImpl
import com.artemissoftware.njordshop.features.products.domain.repository.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductsRepository(dummyjsonApiSource: DummyjsonApiSource, productDao: ProductDao): ProductsRepository {
        return ProductsRepositoryImpl(dummyjsonApiSource = dummyjsonApiSource, productDao = productDao)
    }
}