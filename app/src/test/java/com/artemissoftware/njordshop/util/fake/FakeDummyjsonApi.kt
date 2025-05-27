package com.artemissoftware.njordshop.util.fake

import com.artemissoftware.njordshop.core.network.DummyjsonApi
import com.artemissoftware.njordshop.core.network.dto.CatalogDto
import com.artemissoftware.njordshop.util.data.DtoTestData

class FakeDummyjsonApi: DummyjsonApi {

    var shouldThrowError = false

    override suspend fun getProducts(limit: Int, skip: Int): CatalogDto {
        if (shouldThrowError) throw Exception("Network error while fetching duration")
        return DtoTestData.catalogDto
    }
}