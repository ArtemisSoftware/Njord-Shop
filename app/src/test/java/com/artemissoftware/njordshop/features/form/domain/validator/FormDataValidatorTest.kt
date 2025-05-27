package com.artemissoftware.njordshop.features.form.domain.validator

import com.artemissoftware.njordshop.util.fake.FakeEmailPatternValidator
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class FormDataValidatorTest {
    private lateinit var fakeEmailPatternValidator: FakeEmailPatternValidator
    private lateinit var formDataValidator: FormDataValidator

    @BeforeEach
    fun setUp() {
        fakeEmailPatternValidator = FakeEmailPatternValidator()
        formDataValidator = FormDataValidator(fakeEmailPatternValidator)
    }

    @Test
    fun `isValidName returns false when blank`() {
        assertFalse(formDataValidator.isValidName(""))
    }

    @Test
    fun `isValidName returns true when not blank`() {
        assertTrue(formDataValidator.isValidName("Alice"))
    }

    @Test
    fun `isValidEmail returns true`() {
        assertTrue(formDataValidator.isValidEmail("test@example.com"))
    }

    @Test
    fun `isValidEmail returns false`() {
        assertFalse(formDataValidator.isValidEmail("test_example_com"))
    }

    @Test
    fun `isValidNumber returns false when blank`() {
        assertFalse(formDataValidator.isValidNumber(""))
    }

    @Test
    fun `isValidNumber returns false when contains non-digit`() {
        assertFalse(formDataValidator.isValidNumber("123a4"))
    }

    @Test
    fun `isValidNumber returns true when only digits`() {
        assertTrue(formDataValidator.isValidNumber("123456"))
    }

    @Test
    fun `isValidDate returns false on Monday`() {
        val monday = LocalDate.of(2024, 5, 20)
        assertFalse(formDataValidator.isValidDate(monday))
    }

    @Test
    fun `isValidDate returns false for future date`() {
        val futureDate = LocalDate.now().plusDays(1)
        assertFalse(formDataValidator.isValidDate(futureDate))
    }

    @Test
    fun `isValidDate returns true for valid past date not on Monday`() {
        val monday = LocalDate.of(2024, 5, 20)
        val date = monday.minusDays(1)
        assertTrue(formDataValidator.isValidDate(date))
    }

    @Test
    fun `isValidPromoCode returns false when blank`() {
        assertFalse(formDataValidator.isValidPromoCode(""))
    }

    @Test
    fun `isValidPromoCode returns false when too short`() {
        assertFalse(formDataValidator.isValidPromoCode("AB"))
    }

    @Test
    fun `isValidPromoCode returns false when too long`() {
        assertFalse(formDataValidator.isValidPromoCode("ABCDEFGH"))
    }

    @Test
    fun `isValidPromoCode returns false when contains lowercase`() {
        assertFalse(formDataValidator.isValidPromoCode("abcd"))
    }

    @Test
    fun `isValidPromoCode returns false when contains lowercase or symbols`() {
        assertFalse(formDataValidator.isValidPromoCode("abc@"))
    }

    @Test
    fun `isValidPromoCode returns true when valid format`() {
        assertTrue(formDataValidator.isValidPromoCode("ABC-DEF"))
    }

    @Test
    fun `isValidPromoCode returns false with accents and normalizes`() {
        assertFalse(formDataValidator.isValidPromoCode("ÁBC-DÊ"))
    }

    @Test
    fun `isValidPromoCode returns false with lowercase accents and normalizes`() {
        assertFalse(formDataValidator.isValidPromoCode("ábc-dõ"))
    }
}