package com.artemissoftware.njordshop.features.products.presentation.catalog

import androidx.paging.PagingData
import com.artemissoftware.njordshop.core.presentation.ui.models.ErrorData
import com.artemissoftware.njordshop.features.products.domain.models.ProductEntry
import kotlinx.coroutines.flow.Flow

internal data class CatalogState(
    val isLoading: Boolean = false,
    val products: Flow<PagingData<ProductEntry>>? = null,
    val searchQuery: String = "",
    val isSearching: Boolean = false,
    val searchResult: List<ProductEntry> = emptyList(),
    val error: ErrorData? = null,
)