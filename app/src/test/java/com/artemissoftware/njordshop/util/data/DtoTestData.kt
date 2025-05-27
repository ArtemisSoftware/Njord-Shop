package com.artemissoftware.njordshop.util.data

import com.artemissoftware.njordshop.core.network.dto.CatalogDto
import com.artemissoftware.njordshop.core.network.dto.DimensionsDto
import com.artemissoftware.njordshop.core.network.dto.MetaDto
import com.artemissoftware.njordshop.core.network.dto.ProductDto
import com.artemissoftware.njordshop.core.network.dto.ReviewDto

object DtoTestData {

    val productDto = ProductDto(
        availabilityStatus = "In Stock",
        brand = "Bandai",
        category = "Model Kits",
        description = "High Grade 1/144 RX-78-2 Gundam – A classic model kit from the original Mobile Suit Gundam series.",
        dimensions = DimensionsDto(
            depth = 5.0,
            height = 18.0,
            width = 12.0
        ),
        discountPercentage = 10.0,
        id = 1,
        images = listOf(
            "https://example.com/images/rx78-2-front.jpg,https://example.com/images/rx78-2-back.jpg",
            "https://example.com/images/rx78-2-back.jpg"
        ),
        meta = MetaDto(
            barcode = "4902425781923",
            createdAt = "2024-12-01T10:15:30Z",
            qrCode = "https://example.com/qr/rx78-2",
            updatedAt = "2025-04-22T11:45:10Z"
        ),
        minimumOrderQuantity = 1,
        price = 22.99,
        rating = 4.8,
        returnPolicy = "30-day return policy for unopened items.",
        reviews = listOf(
            ReviewDto(
                comment = "Great build experience and nostalgic design!",
                date = "2025-05-01",
                rating = 5,
                reviewerEmail = "john@example.com",
                reviewerName = "John Mecha"
            ),
            ReviewDto(
                comment = "Good detail for the price. Stickers could be better.",
                date = "2025-05-15",
                rating = 4,
                reviewerEmail = "sara@example.com",
                reviewerName = "Sara Gunpla"
            )
        ),
        shippingInformation = "Ships within 2 business days via tracked shipping.",
        sku = "HG-RX78-2-144",
        stock = 50,
        tags = listOf("Gundam", "Gunpla", "Model Kit", "HG", "RX-78-2"),
        thumbnail = "https://example.com/images/rx78-2-thumbnail.jpg",
        title = "HG 1/144 RX-78-2 Gundam",
        warrantyInformation = "Manufacturer's 1-year warranty included.",
        weight = 300
    )

    val catalogDto = CatalogDto(
        limit = 1,
        products = listOf(productDto),
        skip = 0,
        total = 1
    )
}