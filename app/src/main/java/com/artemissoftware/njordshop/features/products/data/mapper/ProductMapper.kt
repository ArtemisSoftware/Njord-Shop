package com.artemissoftware.njordshop.features.products.data.mapper

import com.artemissoftware.njordshop.core.database.entities.ProductEntity
import com.artemissoftware.njordshop.core.network.dto.ProductDto
import com.artemissoftware.njordshop.features.products.domain.models.Product
import com.artemissoftware.njordshop.features.products.domain.models.ProductEntry

internal fun ProductDto.toEntity(): ProductEntity{
    return ProductEntity(
        id = id,
        title = title,
        discountPercentage = discountPercentage,
        images = images.first(),
        rating = rating,
        thumbnail = thumbnail,
        price = price,
        description = description,
        stock = stock
    )
}

internal fun ProductEntity.toProduct(): Product{
    return Product(
        id = id,
        title = title,
        discountPercentage = discountPercentage,
        images = listOf(images),
        price = price,
        rating = rating,
        stock = stock
    )
}

internal fun ProductEntity.toProductEntry(): ProductEntry{
    return ProductEntry(
        id = id,
        title = title,
        thumbnail = thumbnail,
        rating = rating,
    )
}