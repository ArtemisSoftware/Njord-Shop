package com.artemissoftware.njordshop.features.form.presentation.models

import com.artemissoftware.njordshop.R
import com.artemissoftware.njordshop.core.presentation.ui.composables.dropdown.DropdownItem
import com.artemissoftware.njordshop.core.presentation.ui.composables.text.UiText

internal object Grades {
    private val BAD = DropdownItem(id = 1, description = UiText.StringResource(R.string.bad))
    private val AVERAGE = DropdownItem(id = 2, description = UiText.StringResource(R.string.satisfactory))
    private val GOOD = DropdownItem(id = 3, description = UiText.StringResource(R.string.good))
    private val VERY_GOOD = DropdownItem(id = 4, description = UiText.StringResource(R.string.very_good))
    private val EXCELLENT = DropdownItem(id = 5, description = UiText.StringResource(R.string.excellent))

    val gradeTypes = listOf(BAD, AVERAGE, GOOD, VERY_GOOD, EXCELLENT)
}