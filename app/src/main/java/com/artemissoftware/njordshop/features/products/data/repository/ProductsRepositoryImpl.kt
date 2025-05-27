package com.artemissoftware.njordshop.features.products.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.artemissoftware.njordshop.core.database.util.buildSearchQuery
import com.artemissoftware.njordshop.core.database.dao.ProductDao
import com.artemissoftware.njordshop.core.database.entities.ProductEntity
import com.artemissoftware.njordshop.core.domain.Resource
import com.artemissoftware.njordshop.core.domain.error.NjordShopError
import com.artemissoftware.njordshop.core.network.source.DummyjsonApiSource
import com.artemissoftware.njordshop.core.network.util.HandleApi
import com.artemissoftware.njordshop.features.products.data.mapper.toEntity
import com.artemissoftware.njordshop.features.products.data.mapper.toProduct
import com.artemissoftware.njordshop.features.products.data.mapper.toProductEntry
import com.artemissoftware.njordshop.features.products.domain.models.Product
import com.artemissoftware.njordshop.features.products.domain.models.ProductEntry
import com.artemissoftware.njordshop.features.products.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductsRepositoryImpl(
    private val dummyjsonApiSource: DummyjsonApiSource,
    private val productDao: ProductDao,
): ProductsRepository {

    override fun getCatalog(): Flow<PagingData<ProductEntry>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 20,
            ),
            pagingSourceFactory = { productDao.pagingSource() },
        ).flow
            .map { value: PagingData<ProductEntity> ->
                value.map { it.toProductEntry() }
            }
    }

    override suspend fun search(query: String): Resource<List<ProductEntry>> {
        val result = (productDao.searchProducts(query) + fallBackSearch(query)).distinctBy { it.id }

        return if(result.isEmpty()){
            Resource.Failure(NjordShopError.SearchWithNoResults)
        }
        else {
            Resource.Success(result.map { it.toProductEntry() })
        }
    }

    private suspend fun fallBackSearch(query: String): List<ProductEntity> {

        val terms = query.trim().split("\\s+".toRegex())
        val likeQuery = buildSearchQuery(terms)
        val result = productDao.searchWithQuery(likeQuery)

        return result
    }

    override suspend fun getProduct(id: Int): Resource<Product> {

        productDao.getProduct(id)?.let {
            return Resource.Success(it.toProduct())
        } ?: run {
            return Resource.Failure(NjordShopError.NoDetailFound)
        }
    }

    override suspend fun preloadAllProducts(): Resource<Unit> {
        val numberOfProducts = productDao.getCount()
        if (numberOfProducts > 0) return Resource.Success(Unit)

        val pageSize = 30
        var skip = 0
        var total = Int.MAX_VALUE // temp value
        val allProducts = mutableListOf<ProductEntity>()

        while (skip < total) {
            val response = HandleApi.safeNetworkCall { dummyjsonApiSource.getProducts(limit = pageSize, skip = skip) }

            when(response){
                is Resource.Success -> {
                    val result = response.data
                    val entities = result.products.map { it.toEntity() }

                    allProducts.addAll(entities)

                    skip += pageSize
                    total = result.total
                }
                is Resource.Failure -> {
                    productDao.deleteAll()
                    return Resource.Failure(response.error)
                }
            }
        }

        productDao.clearAndSave(allProducts)
        return Resource.Success(Unit)
    }

}