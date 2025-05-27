package com.artemissoftware.njordshop.core.presentation.designsystem.composables.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.artemissoftware.njordshop.R
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.NjordShopTheme
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.ThemePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NSSearchBar(
    text: String,
    placeHolderText: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    onClearSearch: () -> Unit,
    modifier: Modifier = Modifier,
    isSearching: Boolean = false,
) {
    val colors1 = SearchBarDefaults.colors()
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = text,
                onQueryChange = onQueryChange,
                onSearch = onSearch,
                expanded = isSearching,
                onExpandedChange = onActiveChange,
                placeholder = {
                    Text(
                        text = placeHolderText,
                        style = MaterialTheme.typography.bodySmall,
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null,
                        tint = if(isSearching) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                },
                trailingIcon = {
                    AnimatedVisibility(visible = isSearching) {
                        Icon(
                            modifier = Modifier.clickable {
                                onActiveChange(false)
                            },
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = null,
                        )
                    }
                    AnimatedVisibility(visible = !isSearching) {
                        Icon(
                            modifier = Modifier.clickable {
                                onClearSearch()
                            },
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                        )
                    }
                },
                colors = colors1.inputFieldColors,
            )
        },
        expanded = isSearching,
        onExpandedChange = onActiveChange,
        modifier = modifier,
        colors = colors1,
        content =  {

        },
    )
}

@ThemePreviews
@Composable
private fun NSSearchBarPreview() {
    NjordShopTheme  {
        NSSearchBar(
            modifier = Modifier
                .fillMaxWidth(),
            text = "",
            placeHolderText = "Search",
            onQueryChange = { },
            onSearch = { },
            onActiveChange = { },
            onClearSearch = { },
        )
    }
}
