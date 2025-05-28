package com.artemissoftware.njordshop.features.products.presentation

import com.artemissoftware.njordshop.features.products.domain.models.Product
import com.artemissoftware.njordshop.features.products.domain.models.ProductEntry
import com.artemissoftware.njordshop.features.products.presentation.catalog.CatalogState
import com.artemissoftware.njordshop.features.products.presentation.detail.DetailState
import com.artemissoftware.njordshop.features.products.presentation.detail.model.toUiModel

internal object PreviewData {

    val product = Product(
        id = 1,
        title = "HG 1/144 RX-78-2 Gundam",
        price = 22.99,
        discountPercentage = 10.0,
        rating = 4.8,
        stock = 50,
        images = listOf(
            "https://example.com/images/rx78-2-front.jpg,https://example.com/images/rx78-2-back.jpg",
        )
    )

    val productEntry = ProductEntry(
        id = 1,
        title = "HG 1/144 RX-78-2 Gundam",
        thumbnail = "https://example.com/images/rx78-2-thumbnail.jpg",
        rating = 4.8
    )

    val detailState = DetailState(product = product.toUiModel())

    val catalogState_searching = CatalogState(
        isSearching = true,
    )

    val catalogState_search_complete = CatalogState(
        isSearching = false,
        searchResult = listOf(productEntry),
        searchQuery = productEntry.title,
    )
}