@file:OptIn(ExperimentalMaterial3Api::class)

package com.artemissoftware.njordshop.features.products.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.artemissoftware.njordshop.R
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.NjordShopTheme
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.ThemePreviews
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.spacing
import com.artemissoftware.njordshop.core.presentation.designsystem.window.WindowContent
import com.artemissoftware.njordshop.core.presentation.ui.composables.scaffold.NSScaffold
import com.artemissoftware.njordshop.core.presentation.ui.composables.scaffold.NSScaffoldDouble
import com.artemissoftware.njordshop.features.products.presentation.PreviewData
import com.artemissoftware.njordshop.features.products.presentation.detail.composables.AboutDescription
import com.artemissoftware.njordshop.features.products.presentation.detail.composables.DetailMediumTopAppBar
import com.artemissoftware.njordshop.features.products.presentation.detail.composables.DetailTopBar
import com.artemissoftware.njordshop.features.products.presentation.detail.model.toProduct

@Composable
internal fun DetailScreen(
    onPopBack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    DetailContent(
        state = state,
        onPopBack = onPopBack,
    )
}

@Composable
private fun DetailContent(
    state: DetailState,
    onPopBack: () -> Unit,
) {
    WindowContent(
        landScapeContent = {
            LandScapeContent(
                state = state,
                onPopBack = onPopBack,
            )
        },
        portraitContent = {
            PortraitContent(
                state = state,
                onPopBack = onPopBack,
            )
        }
    )
}

@Composable
private fun PortraitContent(
    state: DetailState,
    onPopBack: () -> Unit,
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val scrollState = rememberLazyListState()

    var request = ImageRequest
        .Builder(LocalContext.current)
        .placeholder(R.drawable.ic_place_holder)
        .crossfade(true)

    state.product?.let {
        request = request
            .data(it.images.first())
            .error(R.drawable.ic_error_image)
    }

    NSScaffold(
        topBar = {
            DetailMediumTopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                onBackClick = onPopBack,
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        isLoading = state.isLoading,
        content = {

            LazyColumn(
                state = scrollState,
            ) {
                item {
                    val collapseFraction = scrollBehavior.state.collapsedFraction

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 180.dp)
                            .graphicsLayer {
                                val scale = 1f - (0.2f * collapseFraction)
                                scaleX = scale
                                scaleY = scale
                                translationY = -collapseFraction * 50f
                            }
                    ) {
                        AsyncImage(
                            modifier = Modifier.fillMaxSize(),
                            model = request
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                    }
                }

                item {
                    state.product?.let {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .navigationBarsPadding(),
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5)
                        ) {
                            AboutDescription(
                                product = it.toProduct(),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(all = MaterialTheme.spacing.spacing3),
                            )
                        }
                    }
                }
            }
        },
        error = state.error,
    )
}

@Composable
private fun LandScapeContent(
    state: DetailState,
    onPopBack: () -> Unit,
) {
    var request = ImageRequest
        .Builder(LocalContext.current)
        .placeholder(R.drawable.ic_place_holder)
        .crossfade(true)

    state.product?.let {
        request = request
            .data(it.images.first())
            .error(R.drawable.ic_error_image)
    }

    NSScaffoldDouble(
        isLoading = state.isLoading,
        contentLeft = {
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DetailTopBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onBackClick = onPopBack,
                )
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.spacing4),
                    model = request
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )

            }
        },
        contentRight = {

            state.product?.let {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding(),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5)
                ) {
                    AboutDescription(
                        product = it.toProduct(),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(all = MaterialTheme.spacing.spacing3),
                    )
                }
            }
        },
        error = state.error,
    )
}

@ThemePreviews
@Composable
private fun DetailContentPreview() {
    NjordShopTheme {
        DetailContent(
            state = PreviewData.detailState,
            onPopBack = {},
        )
    }
}