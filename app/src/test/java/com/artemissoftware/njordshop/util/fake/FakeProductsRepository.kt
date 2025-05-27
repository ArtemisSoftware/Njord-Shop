package com.artemissoftware.njordshop.util.fake

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.artemissoftware.njordshop.core.domain.Resource
import com.artemissoftware.njordshop.core.domain.error.NjordShopError
import com.artemissoftware.njordshop.features.products.domain.models.Product
import com.artemissoftware.njordshop.features.products.domain.models.ProductEntry
import com.artemissoftware.njordshop.features.products.domain.repository.ProductsRepository
import com.artemissoftware.njordshop.util.data.TestData
import kotlinx.coroutines.flow.Flow

class FakeProductsRepository : ProductsRepository {

    private val sampleProducts = listOf(TestData.productEntry)

    override suspend fun preloadAllProducts(): Resource<Unit> {
        return Resource.Success(Unit)
    }

    override fun getCatalog(): Flow<PagingData<ProductEntry>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { FakePagingSource(sampleProducts) }
        ).flow
    }

    override suspend fun search(query: String): Resource<List<ProductEntry>> {
        val results = sampleProducts.filter {
            it.title.contains(query, ignoreCase = true)
        }
        return Resource.Success(results)
    }

    override suspend fun getProduct(id: Int): Resource<Product> {

        return if(TestData.product.id == id){
            Resource.Success(TestData.product)
        }
        else  Resource.Failure(NjordShopError.NoDetailFound)
    }
}

// A fake PagingSource for demonstration/testing purposes
class FakePagingSource(
    private val items: List<ProductEntry>
) : PagingSource<Int, ProductEntry>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductEntry> {
        return LoadResult.Page(
            data = items,
            prevKey = null,
            nextKey = null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, ProductEntry>): Int? = null
}