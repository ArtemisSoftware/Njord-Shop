package com.artemissoftware.njordshop.core.presentation.ui.composables.dropdown

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.NjordShopTheme
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.ThemePreviews
import com.artemissoftware.njordshop.core.presentation.ui.composables.text.UiText

@Composable
fun NSDropDown(
    title: String,
    items: List<DropdownItem>,
    modifier: Modifier = Modifier,
    selectedValue: DropdownItem? = null,
    onUpdate: (Int) -> Unit,
){
    val context = LocalContext.current
    val isDropDownExpanded = remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .wrapContentSize(Alignment.TopStart)
    ) {

        OutlinedTextField(
            value = (selectedValue?.description ?: items[0].description).asString(context),
            label = { Text(title) },
            enabled = false,
            onValueChange = {},
            trailingIcon = {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        isDropDownExpanded.value = true
                    }
                )
            },
        )

        DropdownMenu(
            expanded = isDropDownExpanded.value,
            onDismissRequest = { isDropDownExpanded.value = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(text = item.description.asString(context))
                    },
                    onClick = {
                        isDropDownExpanded.value = false
                        onUpdate(item.id)
                    }
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun NSDropDownPreview() {
    NjordShopTheme {
        NSDropDown(
            title = "Tipo",
            items = listOf(
                DropdownItem(1, UiText.DynamicString("Selector")),
                DropdownItem(2, UiText.DynamicString("Selector 2"))
            ),
            onUpdate = {}
        )
    }
}