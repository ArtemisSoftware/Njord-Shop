package com.artemissoftware.njordshop.features.products.presentation.catalog.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.artemissoftware.njordshop.R
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.NjordShopTheme
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.ThemePreviews
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.dimension
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.spacing
import com.artemissoftware.njordshop.core.presentation.designsystem.window.WindowContent
import com.artemissoftware.njordshop.core.presentation.ui.util.extensions.shimmerEffect
import com.artemissoftware.njordshop.features.products.domain.models.ProductEntry
import com.artemissoftware.njordshop.features.products.presentation.PreviewData
import com.artemissoftware.njordshop.features.products.presentation.catalog.util.extensions.getNumberOfRatingIcons


@Composable
internal fun ProductEntryCard(
    productEntry: ProductEntry,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Card(
        modifier = modifier,
        onClick = { onClick() },
    ) {
        WindowContent (
            portraitContent = {
                PortraitContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.spacing2),
                    productEntry = productEntry,
                )
            },
            landScapeContent = {
                LandscapeContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.spacing2),
                    productEntry = productEntry,
                )
            }
        )
    }
}

@Composable
fun ShimmerProductEntryCard(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
    ) {

        WindowContent(
            portraitContent = {
                PortraitContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.spacing2),
                )
            },
            landScapeContent = {
                LandscapeContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.spacing2),
                )
            }
        )
    }
}

@Composable
private fun PortraitContent (
    modifier: Modifier = Modifier,
    productEntry: ProductEntry? = null,
) {

    var request = ImageRequest
        .Builder(LocalContext.current)
        .placeholder(R.drawable.ic_place_holder)
        .crossfade(true)

    productEntry?.let {
        request = request
            .data(it.thumbnail)
            .error(R.drawable.ic_error_image)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1)
    ) {

        AsyncImage(
            modifier = Modifier
                .size(MaterialTheme.dimension.cardImage),
            model = request
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Description(
            modifier = Modifier
                .fillMaxWidth(),
            productEntry = productEntry,
        )
    }
}

@Composable
private fun LandscapeContent (
    modifier: Modifier = Modifier,
    productEntry: ProductEntry? = null,
) {
    var request = ImageRequest
        .Builder(LocalContext.current)
        .placeholder(R.drawable.ic_place_holder)
        .crossfade(true)

    productEntry?.let {
        request = request
            .data(it.thumbnail)
            .error(R.drawable.ic_error_image)
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            modifier = Modifier
                .size(MaterialTheme.dimension.cardImage),
            model = request
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Description(
            modifier = Modifier
                .fillMaxWidth(),
            productEntry = productEntry,
        )
    }
}

@Composable
private fun Description(
    modifier: Modifier = Modifier,
    productEntry: ProductEntry? = null,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing0_5),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        productEntry?.let {
            Text(
                text = it.title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(),
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.rating),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier,
                )

                Text(
                    text = it.rating.toString(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier,
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(it.getNumberOfRatingIcons()) {
                    Icon(
                        imageVector = Icons.Filled.ThumbUp,
                        contentDescription = "Rating icon",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }

        } ?: run{
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(MaterialTheme.dimension.shimmerTextHeight)
                    .shimmerEffect()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(MaterialTheme.dimension.shimmerTextHeight)
                    .shimmerEffect()
            )
        }
    }
}

@ThemePreviews
@Composable
private fun ProductEntryCardPreview() {
    NjordShopTheme  {
        ProductEntryCard(
            productEntry = PreviewData.productEntry,
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
        )
    }
}

@ThemePreviews
@Composable
private fun ShimmerProductEntryCardPreview() {
    NjordShopTheme {
        ShimmerProductEntryCard(
            modifier = Modifier.fillMaxWidth(),
        )
    }
}