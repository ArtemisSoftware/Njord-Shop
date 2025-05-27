package com.artemissoftware.njordshop.core.network.di

import com.artemissoftware.njordshop.BuildConfig
import com.artemissoftware.njordshop.core.network.DummyjsonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    @Provides
//    @Singleton
//    fun provideOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(
//                HttpLoggingInterceptor()
//                    .setLevel(HttpLoggingInterceptor.Level.BODY),
//            )
//            .readTimeout(15L, TimeUnit.SECONDS)
//            .connectTimeout(15L, TimeUnit.SECONDS)
//            .build()
//    }

    @Provides
    @Singleton
    fun provideRetrofit(/*okHttpClient: OkHttpClient*/): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            //.client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDummyjsonApi(retrofit: Retrofit): DummyjsonApi {
        return retrofit.create(DummyjsonApi::class.java)
    }
}