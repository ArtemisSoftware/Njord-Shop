package com.artemissoftware.njordshop.features.form.domain.validator

interface PatternValidator {
    fun matches(value: String): Boolean
}