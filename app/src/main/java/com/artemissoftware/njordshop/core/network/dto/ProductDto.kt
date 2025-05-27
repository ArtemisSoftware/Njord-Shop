package com.artemissoftware.njordshop.core.network.dto


import com.squareup.moshi.Json

data class ProductDto(
    @field:Json(name = "availabilityStatus")
    val availabilityStatus: String = "",
    @field:Json(name = "brand")
    val brand: String? = null,
    @field:Json(name = "category")
    val category: String = "",
    @field:Json(name = "description")
    val description: String = "",
    @field:Json(name = "dimensions")
    val dimensions: DimensionsDto = DimensionsDto(),
    @field:Json(name = "discountPercentage")
    val discountPercentage: Double = 0.0,
    @field:Json(name = "id")
    val id: Int = 0,
    @field:Json(name = "images")
    val images: List<String> = listOf(),
    @field:Json(name = "meta")
    val meta: MetaDto = MetaDto(),
    @field:Json(name = "minimumOrderQuantity")
    val minimumOrderQuantity: Int = 0,
    @field:Json(name = "price")
    val price: Double = 0.0,
    @field:Json(name = "rating")
    val rating: Double = 0.0,
    @field:Json(name = "returnPolicy")
    val returnPolicy: String = "",
    @field:Json(name = "reviews")
    val reviews: List<ReviewDto> = listOf(),
    @field:Json(name = "shippingInformation")
    val shippingInformation: String = "",
    @field:Json(name = "sku")
    val sku: String = "",
    @field:Json(name = "stock")
    val stock: Int = 0,
    @field:Json(name = "tags")
    val tags: List<String> = listOf(),
    @field:Json(name = "thumbnail")
    val thumbnail: String = "",
    @field:Json(name = "title")
    val title: String = "",
    @field:Json(name = "warrantyInformation")
    val warrantyInformation: String = "",
    @field:Json(name = "weight")
    val weight: Int = 0
)