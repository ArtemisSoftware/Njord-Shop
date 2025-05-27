package com.artemissoftware.njordshop.features.products.presentation.catalog

internal sealed interface CatalogEvent {
    data class UpdateSearchQuery(val searchQuery: String) : CatalogEvent
    data object Search : CatalogEvent
    data class ActivateSearch(val isActive: Boolean) : CatalogEvent
    data object ClearSearch : CatalogEvent
}