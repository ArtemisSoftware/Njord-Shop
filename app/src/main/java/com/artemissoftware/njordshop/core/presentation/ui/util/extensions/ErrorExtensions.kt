package com.artemissoftware.njordshop.core.presentation.ui.util.extensions

import com.artemissoftware.njordshop.R
import com.artemissoftware.njordshop.core.domain.error.DataError
import com.artemissoftware.njordshop.core.domain.error.NSError
import com.artemissoftware.njordshop.core.domain.error.NjordShopError
import com.artemissoftware.njordshop.core.presentation.ui.composables.text.UiText

fun NSError.toUiText(): UiText {
    return when (this) {
        is DataError.NetworkError -> {
            this.asUiText()
        }
        is NjordShopError -> {
            this.asUiText()
        }
        else -> UiText.StringResource(R.string.error_not_mapped)
    }
}

private fun DataError.NetworkError.asUiText(): UiText {
    return when (this) {
        DataError.NetworkError.Connect -> UiText.StringResource(
            R.string.connection_error,
        )
        is DataError.NetworkError.Error -> UiText.DynamicString(this.message)
        DataError.NetworkError.SocketTimeout -> UiText.StringResource(
            R.string.connection_socket_time_out,
        )
        DataError.NetworkError.Unknown -> UiText.StringResource(
            R.string.unknown_error,
        )
        DataError.NetworkError.UnknownHost -> UiText.StringResource(
            R.string.unknown_host_error,
        )
    }
}

private fun NjordShopError.asUiText(): UiText {
    return when (this) {
        NjordShopError.SearchWithNoResults -> UiText.StringResource(
            R.string.search_no_results,
        )

        NjordShopError.NoDetailFound -> UiText.StringResource(
            R.string.search_detail_not_found,
        )
    }
}