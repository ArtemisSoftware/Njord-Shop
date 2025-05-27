package com.artemissoftware.njordshop.util.fake

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.artemissoftware.njordshop.core.database.dao.ProductDao
import com.artemissoftware.njordshop.core.database.entities.ProductEntity
import com.artemissoftware.njordshop.core.database.entities.ProductFtsEntity

class FakeProductDao : ProductDao {

    private val products = mutableListOf<ProductEntity>()
    private val ftsIndex = mutableMapOf<Int, ProductFtsEntity>() // rowId -> FTS

    override suspend fun insertAll(productEntities: List<ProductEntity>) {
        products.removeAll { existing -> productEntities.any { it.id == existing.id } }
        products.addAll(productEntities)
    }

    override fun pagingSource(): PagingSource<Int, ProductEntity> {
        return InMemoryPagingSource(products)
    }

    override suspend fun deleteAll() {
        products.clear()
        ftsIndex.clear()
    }

    override suspend fun getProduct(id: Int): ProductEntity? {
        return products.find { it.id == id }
    }

    override suspend fun getCount(): Int = products.size

    override suspend fun insertFts(products: List<ProductFtsEntity>) {
        // Simulate rowId by matching index of productEntities
        products.forEachIndexed { index, fts ->
            val product = this.products.getOrNull(index)
            if (product != null) {
                ftsIndex[product.id] = fts
            }
        }
    }

    override suspend fun searchProducts(query: String): List<ProductEntity> {
        val keywords = query.trim().split("\\s+".toRegex())
        val matchedIds = ftsIndex.filter { (_, fts) ->
            keywords.all { word ->
                fts.title.contains(word, ignoreCase = true) ||
                        fts.description.contains(word, ignoreCase = true)
            }
        }.keys

        return products.filter { it.id in matchedIds }
    }

    override suspend fun clearAndSave(productEntities: List<ProductEntity>) {
        deleteAll()
        insertAll(productEntities)
        insertFts(productEntities.map { ProductFtsEntity(title = it.title, description = it.description) })
    }

    class InMemoryPagingSource(
        private val data: List<ProductEntity>
    ) : PagingSource<Int, ProductEntity>() {

        override fun getRefreshKey(state: PagingState<Int, ProductEntity>): Int? = null

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductEntity> {
            val start = params.key ?: 0
            val end = (start + params.loadSize).coerceAtMost(data.size)
            val sublist = data.subList(start, end)
            return LoadResult.Page(
                data = sublist,
                prevKey = if (start == 0) null else start - params.loadSize,
                nextKey = if (end == data.size) null else end
            )
        }
    }
}
