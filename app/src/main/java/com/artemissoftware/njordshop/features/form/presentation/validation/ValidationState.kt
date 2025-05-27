package com.artemissoftware.njordshop.features.form.presentation.validation

import com.artemissoftware.njordshop.core.presentation.ui.composables.dropdown.DropdownItem
import com.artemissoftware.njordshop.features.form.presentation.models.Grades
import java.time.LocalDate


internal data class ValidationState(
    val name: String = "",
    val isNameValid: Boolean = false,
    val email: String = "",
    val isEmailValid: Boolean = false,
    val number: String = "",
    val isNumberValid: Boolean = false,
    val promotionalCode: String = "",
    val isPromotionalCodeValid: Boolean = false,
    val showCalendar: Boolean = false,
    val date: LocalDate? = null,
    val isDateValid: Boolean = false,
    val classificationTypes: List<DropdownItem> = Grades.gradeTypes,
    val classification: Int = Grades.gradeTypes.first().id,
){
    fun isFormValid() = isNameValid && isEmailValid && isNumberValid && isPromotionalCodeValid && isDateValid
}