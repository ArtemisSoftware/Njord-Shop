package com.artemissoftware.njordshop.features.form.presentation.validation

import java.time.LocalDate

sealed interface ValidationEvent {

    data class UpdateName(val name: String) : ValidationEvent
    data class UpdateEmail(val email: String) : ValidationEvent
    data class UpdateNumber(val number: String) : ValidationEvent
    data class UpdatePromotionalCode(val promotionalCode: String) : ValidationEvent
    data object CloseCalendar : ValidationEvent
    data object OpenCalendar : ValidationEvent
    data class UpdateDate(val date: LocalDate) : ValidationEvent
    data class UpdateClassification(val id: Int) : ValidationEvent
}