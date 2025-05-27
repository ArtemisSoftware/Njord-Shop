package com.artemissoftware.njordshop.features.products.presentation.catalog.util.extensions

import com.artemissoftware.njordshop.features.products.domain.models.ProductEntry

internal fun ProductEntry.getNumberOfRatingIcons(): Int{
    return if(rating < 3.0) { 1 }
    else if (rating in 3.0..4.0) { 2 }
    else if (rating > 4.0) { 3 } else 1
}