package com.artemissoftware.njordshop.features.form.presentation.navigation

import kotlinx.serialization.Serializable

sealed class FormRoute {
    @Serializable
    object Validation
}