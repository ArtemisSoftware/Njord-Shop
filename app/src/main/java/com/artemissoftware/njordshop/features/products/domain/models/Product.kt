package com.artemissoftware.njordshop.features.products.domain.models

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val images: List<String>,
)
