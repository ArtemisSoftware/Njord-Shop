package com.artemissoftware.njordshop.features.form.domain.validator

import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject

class FormDataValidator @Inject constructor(
    private val patternValidator: PatternValidator
) {

    fun isValidName(name: String): Boolean {
        return name.isNotBlank()
    }

    fun isValidEmail(email: String): Boolean {
        return patternValidator.matches(email.trim())
    }

    fun isValidNumber(number: String): Boolean {
        return number.isNotBlank() && number.all { it.isDigit() }
    }

    fun isValidPromoCode(code: String): Boolean {
        if (code.isBlank()) return false
        val regex = Regex("^[A-Z-]{3,7}$")
        return code.isNotBlank() && regex.matches(code)
    }

    fun isValidDate(date: LocalDate): Boolean {
        val today = LocalDate.now()
        return date.dayOfWeek != DayOfWeek.MONDAY && !date.isAfter(today)
    }
}