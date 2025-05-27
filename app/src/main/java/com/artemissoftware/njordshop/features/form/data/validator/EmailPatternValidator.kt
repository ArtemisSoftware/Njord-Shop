package com.artemissoftware.njordshop.features.form.data.validator

import android.util.Patterns
import com.artemissoftware.njordshop.features.form.domain.validator.PatternValidator

class EmailPatternValidator(): PatternValidator {

    override fun matches(value: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }
}