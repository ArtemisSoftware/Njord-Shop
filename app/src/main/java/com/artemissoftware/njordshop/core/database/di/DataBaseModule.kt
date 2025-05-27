package com.artemissoftware.njordshop.core.database.di

import android.content.Context
import androidx.room.Room
import com.artemissoftware.njordshop.core.database.NjordShopDataBase
import com.artemissoftware.njordshop.core.database.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideNjordShopDataBase(
        @ApplicationContext context: Context,
    ): NjordShopDataBase {
        return Room
            .databaseBuilder(
                context,
                NjordShopDataBase::class.java,
                NjordShopDataBase.DB_NAME,
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideProductDao(db: NjordShopDataBase): ProductDao{
        return db.getProductDao()
    }
}