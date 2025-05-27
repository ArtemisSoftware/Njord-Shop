package com.artemissoftware.njordshop.core.domain.error

sealed interface NjordShopError: NSError {

    data object SearchWithNoResults : NjordShopError
    data object NoDetailFound : NjordShopError
}