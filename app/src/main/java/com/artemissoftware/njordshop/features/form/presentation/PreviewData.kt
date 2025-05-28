package com.artemissoftware.njordshop.features.form.presentation

import com.artemissoftware.njordshop.features.form.presentation.validation.ValidationState
import java.time.LocalDate

internal object PreviewData {

    val validationState = ValidationState(
        name = "RX-78-2 Gundam",
        email = "Gundam@Gundam.com",
        number = "50",
        promotionalCode = "HG",
        date = LocalDate.now().minusDays(40),
        classification = 1
    )
}