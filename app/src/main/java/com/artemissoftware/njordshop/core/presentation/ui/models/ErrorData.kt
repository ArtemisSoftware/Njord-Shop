package com.artemissoftware.njordshop.core.presentation.ui.models

import android.os.Parcelable
import com.artemissoftware.njordshop.core.presentation.ui.composables.text.UiText
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorData(
    val message: UiText,
    val onClick: (() -> Unit)? = null,
    val buttonText: UiText = UiText.DynamicString(""),
): Parcelable