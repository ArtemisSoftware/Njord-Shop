package com.artemissoftware.njordshop.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val thumbnail: String,
    val stock: Int,
    val images: String, // TODO: provisório, se tiver tempo quero todas
)
