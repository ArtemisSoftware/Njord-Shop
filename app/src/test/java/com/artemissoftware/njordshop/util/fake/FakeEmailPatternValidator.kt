package com.artemissoftware.njordshop.util.fake

import com.artemissoftware.njordshop.features.form.domain.validator.PatternValidator

class FakeEmailPatternValidator: PatternValidator {
    override fun matches(value: String): Boolean {
        return value.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"))
    }
}