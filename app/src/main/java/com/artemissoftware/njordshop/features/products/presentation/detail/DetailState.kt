package com.artemissoftware.njordshop.features.products.presentation.detail

import com.artemissoftware.njordshop.core.presentation.ui.models.ErrorData
import com.artemissoftware.njordshop.features.products.domain.models.Product

internal data class DetailState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: ErrorData? = null
)