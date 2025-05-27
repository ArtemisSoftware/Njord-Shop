package com.artemissoftware.njordshop.features.form.presentation.validation

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.njordshop.features.form.domain.validator.FormDataValidator
import com.artemissoftware.njordshop.util.fake.FakeEmailPatternValidator
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ValidationViewModelTest {

    private lateinit var fakeEmailPatternValidator: FakeEmailPatternValidator
    private lateinit var formDataValidator: FormDataValidator

    private lateinit var viewModel: ValidationViewModel

    @BeforeEach
    fun setUp() {
        fakeEmailPatternValidator = FakeEmailPatternValidator()
        formDataValidator = FormDataValidator(fakeEmailPatternValidator)

        viewModel = ValidationViewModel(
            formDataValidator = formDataValidator
        )
    }

    @Test
    fun `Update name with valid value`() = runTest {

        viewModel.onTriggerEvent(ValidationEvent.UpdateName(name = "Batman"))

        assertThat(viewModel.state.value.name).isEqualTo("Batman")
        assertTrue(viewModel.state.value.isNameValid)
    }

    @Test
    fun `Update name with invalid value`() = runTest {
        viewModel.onTriggerEvent(ValidationEvent.UpdateName(name = ""))

        assertThat(viewModel.state.value.name).isEqualTo("")
        assertFalse(viewModel.state.value.isNameValid)
    }

    @Test
    fun `Update email with valid value`() = runTest {

        viewModel.onTriggerEvent(ValidationEvent.UpdateEmail(email = "Batman@batcave.com"))

        assertThat(viewModel.state.value.email).isEqualTo("Batman@batcave.com")
        assertTrue(viewModel.state.value.isEmailValid)
    }

    @Test
    fun `Update email with invalid value`() = runTest {
        viewModel.onTriggerEvent(ValidationEvent.UpdateEmail(email = "Batman:@batcave.com"))

        assertThat(viewModel.state.value.email).isEqualTo("Batman:@batcave.com")
        assertFalse(viewModel.state.value.isEmailValid)
    }

    @Test
    fun `Update number with valid value`() = runTest {

        viewModel.onTriggerEvent(ValidationEvent.UpdateNumber(number = "123456"))

        assertThat(viewModel.state.value.number).isEqualTo("123456")
        assertTrue(viewModel.state.value.isNumberValid)
    }

    @Test
    fun `Update number with invalid value`() = runTest {
        viewModel.onTriggerEvent(ValidationEvent.UpdateNumber(number = "123a4"))

        assertThat(viewModel.state.value.number).isEqualTo("123a4")
        assertFalse(viewModel.state.value.isNumberValid)
    }

    @Test
    fun `Update promotional code with invalid value`() = runTest {
        viewModel.onTriggerEvent(ValidationEvent.UpdatePromotionalCode(promotionalCode = "123a4"))

        assertThat(viewModel.state.value.promotionalCode).isEqualTo("123a4")
        assertFalse(viewModel.state.value.isPromotionalCodeValid)
    }

    @Test
    fun `Update promotional code with valid value`() = runTest {

        viewModel.onTriggerEvent(ValidationEvent.UpdatePromotionalCode(promotionalCode = "AABBCC"))

        assertThat(viewModel.state.value.promotionalCode).isEqualTo("AABBCC")
        assertTrue(viewModel.state.value.isPromotionalCodeValid)
    }

    @Test
    fun `Update date with invalid value`() = runTest {
        val monday = LocalDate.of(2024, 5, 20)
        viewModel.onTriggerEvent(ValidationEvent.UpdateDate(date = monday))

        assertThat(viewModel.state.value.date).isEqualTo(monday)
        assertFalse(viewModel.state.value.isDateValid)
    }

    @Test
    fun `Update date with valid value`() = runTest {
        val monday = LocalDate.of(2024, 5, 20)
        val date = monday.plusDays(1)

        viewModel.onTriggerEvent(ValidationEvent.UpdateDate(date = date))

        assertThat(viewModel.state.value.date).isEqualTo(date)
        assertTrue(viewModel.state.value.isDateValid)
    }

    @Test
    fun `All data is valid`() = runTest {
        val monday = LocalDate.of(2024, 5, 20)
        val date = monday.plusDays(1)

        viewModel.onTriggerEvent(ValidationEvent.UpdateDate(date = date))
        viewModel.onTriggerEvent(ValidationEvent.UpdatePromotionalCode(promotionalCode = "AABBCC"))
        viewModel.onTriggerEvent(ValidationEvent.UpdateNumber(number = "123456"))
        viewModel.onTriggerEvent(ValidationEvent.UpdateEmail(email = "Batman@batcave.com"))
        viewModel.onTriggerEvent(ValidationEvent.UpdateName(name = "Batman"))

        assertTrue(viewModel.state.value.isFormValid())
    }
}