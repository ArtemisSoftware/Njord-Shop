package com.artemissoftware.njordshop.features.products.presentation.catalog.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.NjordShopTheme
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.ThemePreviews
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.spacing
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.window
import com.artemissoftware.njordshop.features.products.domain.models.ProductEntry

@Composable
fun CatalogGrid(
    state: LazyGridState,
    entries: LazyPagingItems<ProductEntry>,
    onClick: (ProductEntry) -> Unit,
    reloadContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    val gridSpan = getGridItemCount()
    val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(gridSpan) }


    LazyVerticalGrid(
        state = state,
        modifier = modifier,
        columns = GridCells.Fixed(getGridItemCount()),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5),
    ) {
        items(
            count = entries.itemCount,
            key = entries.itemKey { it.id },
            contentType = entries.itemContentType { "product" },
        ) {
            entries[it]?.let { entry ->
                ProductEntryCard(
                    productEntry = entry,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onClick(entry) },
                )
            }
        }

        item(span = span) {
            reloadContent()
        }
    }
}

@Composable
fun CatalogGrid(
    entries: List<ProductEntry>,
    onClick: (ProductEntry) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(getGridItemCount()),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5),
    ) {
        items(entries) { entry ->
            ProductEntryCard(
                productEntry = entry,
                modifier = Modifier.fillMaxWidth(),
                onClick = { onClick(entry) },
            )
        }
    }
}

@Composable
fun ShimmerCatalogGrid(
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(getGridItemCount()),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5),
    ) {
        items(10){
            ShimmerProductEntryCard(
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun getGridItemCount(): Int{
    return if(MaterialTheme.window.isLandScape()){
        3
    }
    else{
        2
    }
}

@ThemePreviews
@Composable
private fun ShimmerCatalogGridPreview() {
    NjordShopTheme  {
        ShimmerCatalogGrid(
            modifier = Modifier.fillMaxWidth(),
        )
    }
}