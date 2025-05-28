@file:OptIn(ExperimentalMaterial3Api::class)

package com.artemissoftware.njordshop.features.form.presentation.validation.composables

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.njordshop.R
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.NjordShopTheme
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.ThemePreviews
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@Composable
internal fun DeliveryDateDialog(
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit,
    showPicker: Boolean
) {

    if (showPicker) {

        val datePickerState = rememberDatePickerState()

        DatePickerDialog(
            onDismissRequest = onDismiss,
            confirmButton = {

                val (isValid, localDate) = isValidateDate(datePickerState.selectedDateMillis)

                TextButton(
                    onClick = {
                        localDate?.let(onDateSelected)
                    },
                    enabled = isValid
                ) {
                    Text(stringResource(R.string.ok))
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(stringResource(R.string.cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

private fun isValidateDate(millis: Long?): Pair<Boolean, LocalDate?> {
    val localDate = millis?.let {
        Instant.ofEpochMilli(it)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }

    val isValid = localDate != null &&
            localDate.dayOfWeek != DayOfWeek.MONDAY &&
            !localDate.isAfter(LocalDate.now())

    return isValid to localDate
}

@ThemePreviews
@Composable
private fun DeliveryDateDialogPreview() {
    NjordShopTheme {
        DeliveryDateDialog(
            onDateSelected = {},
            onDismiss = {},
            showPicker = true
        )
    }
}