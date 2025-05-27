package com.artemissoftware.njordshop.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.artemissoftware.njordshop.core.database.dao.ProductDao
import com.artemissoftware.njordshop.core.database.entities.ProductEntity
import com.artemissoftware.njordshop.core.database.entities.ProductFtsEntity

@Database(
    entities = [
        ProductEntity::class,
        ProductFtsEntity::class
    ],
    version = 1,
    exportSchema = false,
)
abstract class NjordShopDataBase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    companion object{
        const val DB_NAME = "njord_db"
    }
}