package com.artemissoftware.njordshop.core.network.dto


import com.squareup.moshi.Json

data class CatalogDto(
    @field:Json(name = "limit")
    val limit: Int,
    @field:Json(name = "products")
    val products: List<ProductDto>,
    @field:Json(name = "skip")
    val skip: Int,
    @field:Json(name = "total")
    val total: Int
)