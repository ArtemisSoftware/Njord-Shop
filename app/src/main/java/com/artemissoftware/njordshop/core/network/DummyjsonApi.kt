package com.artemissoftware.njordshop.core.network

import com.artemissoftware.njordshop.core.network.dto.CatalogDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DummyjsonApi {

    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): CatalogDto
}