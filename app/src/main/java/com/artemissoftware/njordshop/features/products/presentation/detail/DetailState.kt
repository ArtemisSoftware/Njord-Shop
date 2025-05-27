package com.artemissoftware.njordshop.features.products.presentation.detail

import android.os.Parcelable
import com.artemissoftware.njordshop.core.presentation.ui.models.ErrorData
import com.artemissoftware.njordshop.features.products.domain.models.Product
import com.artemissoftware.njordshop.features.products.presentation.detail.model.ProductUiModel
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class DetailState(
    val isLoading: Boolean = false,
    val product: ProductUiModel? = null,
    val error: ErrorData? = null
): Parcelable