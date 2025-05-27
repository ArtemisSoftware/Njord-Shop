package com.artemissoftware.njordshop.features.form.presentation.validation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.artemissoftware.njordshop.R
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.NjordShopTheme
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.spacing
import com.artemissoftware.njordshop.core.presentation.ui.composables.dropdown.NSDropDown
import com.artemissoftware.njordshop.core.presentation.ui.composables.scaffold.NSScaffold
import com.artemissoftware.njordshop.features.form.presentation.validation.composables.DeliveryDateDialog

@Composable
internal fun ValidationScreen(
    viewModel: ValidationViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ValidationContent(
        state = state,
        onEvent = viewModel::onTriggerEvent
    )
}

@Composable
private fun ValidationContent(
    state: ValidationState,
    onEvent: (ValidationEvent) -> Unit,
) {
    val context = LocalContext.current

    NSScaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.spacing.spacing2),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing2)
            ) {

                Text(
                    text = stringResource(id = R.string.form),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.displayMedium
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.7F),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing4)
                ) {

                    item {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(stringResource(id = R.string.name)) },
                            value = state.name,
                            onValueChange = {
                                onEvent(ValidationEvent.UpdateName(it))
                            },
                            isError = if(state.name.isEmpty()) false else !state.isNameValid,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Next
                            ),
                        )
                    }

                    item {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(stringResource(id = R.string.email)) },
                            value = state.email,
                            onValueChange = {
                                onEvent(ValidationEvent.UpdateEmail(it))
                            },
                            isError = if(state.email.isEmpty()) false else !state.isEmailValid,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Email
                            ),
                        )
                    }

                    item {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(stringResource(id = R.string.number)) },
                            value = state.number,
                            onValueChange = {
                                onEvent(ValidationEvent.UpdateNumber(it))
                            },
                            isError = if(state.number.isEmpty()) false else !state.isNumberValid,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Number
                            ),
                        )
                    }

                    item {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(stringResource(id = R.string.promotional_code)) },
                            value = state.promotionalCode,
                            onValueChange = {
                                if(it.length <= 7) {
                                    onEvent(ValidationEvent.UpdatePromotionalCode(it))
                                }
                            },
                            isError = if(state.promotionalCode.isEmpty()) false else !state.isPromotionalCodeValid,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Next,
                            ),
                        )
                    }
                    item {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing2),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(stringResource(id = R.string.delivery_date)+ " ${state.date ?: ""}")

                            Button(onClick = { onEvent(ValidationEvent.OpenCalendar) }) {
                                Text(stringResource(id = R.string.open_calendar))
                            }
                        }
                    }
                    item {
                        NSDropDown(
                            title = stringResource(id = R.string.rate),
                            selectedValue = state.classificationTypes.find { it.id == state.classification },
                            items = state.classificationTypes,
                            onUpdate = { onEvent(ValidationEvent.UpdateClassification(it)) }
                        )
                    }
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.isFormValid(),
                    onClick = {
                        Toast.makeText(context, context.getString(R.string.form_validated), Toast.LENGTH_LONG).show()
                    },
                    content = {
                        Text(text = stringResource(id = R.string.submit))
                    }
                )

                DeliveryDateDialog(
                    showPicker = state.showCalendar,
                    onDateSelected = { onEvent(ValidationEvent.UpdateDate(it)) },
                    onDismiss = { onEvent(ValidationEvent.CloseCalendar) }
                )
            }
        }
    )
}

@Preview
@Composable
private fun ValidationContentPreview() {
    NjordShopTheme {
        ValidationContent(
            state = ValidationState(),
            onEvent = {}
        )
    }
}