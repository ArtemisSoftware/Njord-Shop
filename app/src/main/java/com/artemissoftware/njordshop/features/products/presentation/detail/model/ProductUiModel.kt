package com.artemissoftware.njordshop.features.products.presentation.detail.model

import android.os.Parcelable
import com.artemissoftware.njordshop.features.products.domain.models.Product
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class ProductUiModel(
    val id: Int,
    val title: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val images: List<String>,
) : Parcelable


internal fun Product.toUiModel() = ProductUiModel(
    id, title, price, discountPercentage, rating, stock, images
)

internal fun ProductUiModel.toProduct() = Product(
    id, title, price, discountPercentage, rating, stock, images
)