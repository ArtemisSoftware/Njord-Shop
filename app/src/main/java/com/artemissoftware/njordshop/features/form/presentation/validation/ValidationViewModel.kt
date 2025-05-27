package com.artemissoftware.njordshop.features.form.presentation.validation

import androidx.lifecycle.ViewModel
import com.artemissoftware.njordshop.features.form.domain.validator.FormDataValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
internal class ValidationViewModel @Inject constructor(
    private val formDataValidator: FormDataValidator,
) : ViewModel() {

    private val _state = MutableStateFlow(ValidationState())
    val state = _state.asStateFlow()

    fun onTriggerEvent(event: ValidationEvent) {
        when (event) {
            is ValidationEvent.UpdateEmail -> { updateEmail(event.email) }
            is ValidationEvent.UpdateName -> { updateName(event.name) }
            is ValidationEvent.UpdateNumber -> { updateNumber(event.number) }
            is ValidationEvent.UpdatePromotionalCode ->{ updatePromotionalCode(event.promotionalCode) }
            ValidationEvent.CloseCalendar -> { updateCalendarVisibility(false) }
            ValidationEvent.OpenCalendar -> { updateCalendarVisibility(true) }
            is ValidationEvent.UpdateDate -> { updateDate(event.date) }
            is ValidationEvent.UpdateClassification -> { updateClassification(event.id) }
        }
    }

    private fun updateName(name: String) = with(_state){

        val isValid = formDataValidator.isValidName(name)

        update {
           it.copy(
               name = name,
               isNameValid = isValid
           )
        }
    }

    private fun updateEmail(email: String) = with(_state){

        val isValid = formDataValidator.isValidEmail(email)

        update {
            it.copy(
                email = email,
                isEmailValid = isValid
            )
        }
    }

    private fun updateNumber(number: String) = with(_state){

        val isValid = formDataValidator.isValidNumber(number)

        update {
            it.copy(
                number = number,
                isNumberValid = isValid
            )
        }
    }

    private fun updatePromotionalCode(promotionalCode: String) = with(_state){

        val isValid = formDataValidator.isValidPromoCode(promotionalCode)

        update {
            it.copy(
                promotionalCode = promotionalCode,
                isPromotionalCodeValid = isValid
            )
        }
    }

    private fun updateCalendarVisibility(isVisible: Boolean) = with(_state){
        update {
            it.copy(
                showCalendar = isVisible
            )
        }
    }

    private fun updateDate(date: LocalDate) = with(_state){

        val isValid = formDataValidator.isValidDate(date)

        update {
            it.copy(
                date = date,
                isDateValid = isValid,
                showCalendar = !isValid,
            )
        }
    }

    private fun updateClassification(id: Int) = with(_state){
        update {
            it.copy(
                classification = id,
            )
        }
    }
}