package com.artemissoftware.njordshop.core.network.source

import com.artemissoftware.njordshop.core.network.DummyjsonApi
import com.artemissoftware.njordshop.core.network.dto.CatalogDto
import com.artemissoftware.njordshop.core.network.util.HandleApi
import javax.inject.Inject

class DummyjsonApiSource @Inject constructor(
    private val dummyjsonApi: DummyjsonApi,
) {

    suspend fun getProducts(limit: Int, skip: Int): CatalogDto {
        return HandleApi.safeApiCall { dummyjsonApi.getProducts(limit = limit, skip = skip) }
    }
}