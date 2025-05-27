package com.artemissoftware.njordshop.features.products.presentation.detail.composables
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.artemissoftware.njordshop.R
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.NjordShopTheme
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.ThemePreviews
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.spacing
import com.artemissoftware.njordshop.features.products.domain.models.Product

@Composable
internal fun AboutDescription(
    product: Product,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing2)
    ) {
        Description(
            text = product.title,
            modifier = Modifier
                .fillMaxWidth(),
        )

        Description(
            title = R.string.price,
            text = product.price.toString() + stringResource(id = R.string.euro),
            modifier = Modifier
                .fillMaxWidth(),
        )

        Description(
            title = R.string.discount,
            text = product.discountPercentage.toString() + stringResource(id = R.string.percentage),
            modifier = Modifier
                .fillMaxWidth(),
        )

        Description(
            title = R.string.stock,
            text = product.stock.toString(),
            modifier = Modifier
                .fillMaxWidth(),
        )

        Description(
            title = R.string.rating,
            text = product.rating.toString(),
            modifier = Modifier
                .fillMaxWidth(),
        )


    }
}

@Composable
private fun Description(
    @StringRes title: Int? = null,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing0_5),
        verticalAlignment = Alignment.CenterVertically
    ) {
        title?.let {
            Text(
                text = stringResource(id = it),
                style = MaterialTheme.typography.titleMedium,
            )
        }

        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}


@ThemePreviews
@Composable
private fun AboutDescriptionPreview() {
    NjordShopTheme {
//        AboutDescription(
//            product = PreviewData.pokemon,
//            modifier = Modifier.fillMaxWidth(),
//        )
    }
}


