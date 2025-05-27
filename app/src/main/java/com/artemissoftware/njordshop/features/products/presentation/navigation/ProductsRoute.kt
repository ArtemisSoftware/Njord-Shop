package com.artemissoftware.njordshop.features.products.presentation.navigation

import kotlinx.serialization.Serializable

sealed class ProductsRoute {

    @Serializable
    object Catalog

    @Serializable
    data class Detail(val id: Int)
}