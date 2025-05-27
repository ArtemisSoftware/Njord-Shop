package com.artemissoftware.njordshop.features.products.domain.repository

import androidx.paging.PagingData
import com.artemissoftware.njordshop.core.domain.Resource
import com.artemissoftware.njordshop.features.products.domain.models.Product
import com.artemissoftware.njordshop.features.products.domain.models.ProductEntry
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun preloadAllProducts(): Resource<Unit>

    fun getCatalog(): Flow<PagingData<ProductEntry>>

    suspend fun search(query: String): Resource<List<ProductEntry>>

    suspend fun getProduct(id: Int): Resource<Product>
}