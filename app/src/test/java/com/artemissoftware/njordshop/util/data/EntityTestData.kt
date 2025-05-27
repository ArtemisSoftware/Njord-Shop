package com.artemissoftware.njordshop.util.data

import com.artemissoftware.njordshop.core.database.entities.ProductEntity

object EntityTestData {

    val productEntity = ProductEntity(
        id = 1,
        title = "HG 1/144 RX-78-2 Gundam",
        price = 22.99,
        discountPercentage = 10.0,
        rating = 4.8,
        thumbnail = "https://example.com/images/rx78-2-thumbnail.jpg",
        stock = 50,
        images = "https://example.com/images/rx78-2-front.jpg,https://example.com/images/rx78-2-back.jpg",
        description = "High Grade 1/144 RX-78-2 Gundam – A classic model kit from the original Mobile Suit Gundam series."
    )
}