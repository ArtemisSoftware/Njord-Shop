package com.artemissoftware.njordshop.core.presentation.ui.models

import com.artemissoftware.njordshop.core.presentation.ui.composables.text.UiText

data class ErrorData(
    val message: UiText,
    val onClick: (() -> Unit)? = null,
    val buttonText: UiText = UiText.DynamicString(""),
)