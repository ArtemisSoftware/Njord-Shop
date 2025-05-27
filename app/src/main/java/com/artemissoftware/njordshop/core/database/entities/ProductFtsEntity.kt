package com.artemissoftware.njordshop.core.database.entities

import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Entity(tableName = "products_fts")
@Fts4(contentEntity = ProductEntity::class)
data class ProductFtsEntity(
    val title: String,
    val description: String
)
