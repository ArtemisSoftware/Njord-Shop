package com.artemissoftware.njordshop.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.artemissoftware.njordshop.core.database.entities.ProductEntity
import com.artemissoftware.njordshop.core.database.entities.ProductFtsEntity

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(productEntities: List<ProductEntity>)

    @Query("SELECT * FROM products")
    fun pagingSource(): PagingSource<Int, ProductEntity>

    @Query("DELETE FROM products")
    suspend fun deleteAll()

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProduct(id: Int): ProductEntity?

    @Query("SELECT COUNT(*) FROM products")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFts(products: List<ProductFtsEntity>)

    @Query("""
        SELECT prd.* FROM products prd 
        JOIN products_fts fts ON prd.id = fts.rowid 
        WHERE products_fts MATCH :query
    """)
    suspend fun searchProducts(query: String): List<ProductEntity>

    @Transaction
    suspend fun clearAndSave(productEntities: List<ProductEntity>){
        deleteAll()
        insertAll(productEntities)
    }
}