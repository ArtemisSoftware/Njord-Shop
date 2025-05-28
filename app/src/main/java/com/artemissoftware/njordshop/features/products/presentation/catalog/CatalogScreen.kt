package com.artemissoftware.njordshop.features.products.presentation.catalog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.artemissoftware.njordshop.R
import com.artemissoftware.njordshop.core.presentation.designsystem.composables.search.NSSearchBar
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.NjordShopTheme
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.ThemePreviews
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.spacing
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.window
import com.artemissoftware.njordshop.core.presentation.ui.composables.pagination.PaginationContent
import com.artemissoftware.njordshop.core.presentation.ui.composables.placeholder.PlaceHolderContent
import com.artemissoftware.njordshop.core.presentation.ui.composables.placeholder.PlaceHolderNotice
import com.artemissoftware.njordshop.core.presentation.ui.composables.scaffold.NSScaffold
import com.artemissoftware.njordshop.features.products.presentation.PreviewData
import com.artemissoftware.njordshop.features.products.presentation.catalog.composables.CatalogGrid
import com.artemissoftware.njordshop.features.products.presentation.catalog.composables.ShimmerCatalogGrid


@Composable
internal fun CatalogScreen(
    navigateToDetail: (Int) -> Unit,
    viewModel: CatalogViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CatalogContent(
        state = state,
        navigateToDetail = navigateToDetail,
        onEvent = viewModel::onTriggerEvent
    )
}

@Composable
private fun CatalogContent(
    state: CatalogState,
    navigateToDetail: (Int) -> Unit,
    onEvent: (CatalogEvent) -> Unit,
) {

    val gridState = rememberLazyGridState()
    val searchBarSize = animateDpAsState(
        targetValue = if (state.isSearching || MaterialTheme.window.isLandScape()) 0.dp else MaterialTheme.spacing.spacing3,
        animationSpec = tween(
            durationMillis = 1000,
        ),
        label = "",
    )

    NSScaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5)
            ) {

                NSSearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = searchBarSize.value),
                    text = state.searchQuery,
                    placeHolderText = stringResource(id = R.string.product),
                    isSearching = state.isSearching,
                    onQueryChange = {
                        onEvent(CatalogEvent.UpdateSearchQuery(searchQuery = it))
                    },
                    onSearch = {
                        onEvent(CatalogEvent.Search)
                    },
                    onActiveChange = {
                        onEvent(CatalogEvent.ActivateSearch(isActive = it))
                    },
                    onClearSearch = {
                        onEvent(CatalogEvent.ClearSearch)
                    }
                )

                AnimatedVisibility(
                    modifier = Modifier.padding(top = MaterialTheme.spacing.spacing1_5),
                    visible = !state.isSearching && state.error == null,
                    exit = fadeOut()
                ) {
                    if (state.searchQuery.isNotEmpty()) {
                        CatalogGrid(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = MaterialTheme.spacing.spacing3),
                            entries = state.searchResult,
                            onClick = { entry ->
                                navigateToDetail(entry.id)
                            },
                        )
                    } else {
                        state.products?.let {

                            val item = it.collectAsLazyPagingItems()

                            PaginationContent(
                                items = item,
                                loadingContent = {
                                    ShimmerCatalogGrid(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = MaterialTheme.spacing.spacing3)
                                    )
                                },
                                content = { productEntries, _ ->
                                    CatalogGrid(
                                        state = gridState,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = MaterialTheme.spacing.spacing3),
                                        entries = productEntries,
                                        onClick = { entry ->
                                            navigateToDetail(entry.id)
                                        }
                                    )
                                }
                            )
                        }
                    }
                }
            }
        },
        isLoading = state.isLoading,
        error = if(state.isSearching) null else state.error,
    )
}

@ThemePreviews
@Composable
private fun CatalogContent_SearchComplete_Preview() {
    NjordShopTheme {
        CatalogContent(
            state = PreviewData.catalogState_search_complete,
            onEvent = {},
            navigateToDetail = {},
        )
    }
}

@ThemePreviews
@Composable
private fun CatalogContent_Searching_Preview() {
    NjordShopTheme {
        CatalogContent(
            state = PreviewData.catalogState_searching,
            onEvent = {},
            navigateToDetail = {},
        )
    }
}