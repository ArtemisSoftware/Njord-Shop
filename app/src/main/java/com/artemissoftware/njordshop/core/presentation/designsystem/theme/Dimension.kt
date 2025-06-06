package com.artemissoftware.njordshop.core.presentation.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

data class Dimension(
    val cardImage: DpSize,
    val iconChipSmall: DpSize,
    val iconChipSize: DpSize,
    val iconSize: DpSize,
    val iconSizeBig: DpSize,
    val iconContentSize: DpSize,
    val statMaxWidth: Dp,
    val shimmerTextHeight: Dp,
    val imageMedium: DpSize,
    val imageLarge: DpSize,
    val progressBarHeightSmall: Dp,
    val iconBorderWidth: Dp,
    val iconBorderWidthSmall: Dp,
)

val dimensionPortrait = Dimension(
    cardImage = DpSize(width = 120.dp, height = 120.dp),
    iconSize = DpSize(width = 46.dp, height = 46.dp),
    iconSizeBig = DpSize(width = 120.dp, height = 120.dp),
    iconChipSmall = DpSize(width = 12.dp, height = 12.dp),
    iconChipSize = DpSize(width = 18.dp, height = 18.dp),
    iconContentSize = DpSize(width = 20.dp, height = 20.dp),
    statMaxWidth = 48.dp,
    shimmerTextHeight = 20.dp,
    imageMedium = DpSize(width = 120.dp, height = 120.dp),
    imageLarge = DpSize(width = 220.dp, height = 220.dp),
    progressBarHeightSmall = 8.dp,
    iconBorderWidth = 2.dp,
    iconBorderWidthSmall = 1.dp,
)


val dimensionLandScape = Dimension(
    cardImage = DpSize(width = 60.dp, height = 60.dp), //
    iconSize = DpSize(width = 46.dp, height = 46.dp),
    iconSizeBig = DpSize(width = 120.dp, height = 120.dp),
    iconChipSmall = DpSize(width = 12.dp, height = 12.dp),
    iconChipSize = DpSize(width = 18.dp, height = 18.dp),
    iconContentSize = DpSize(width = 20.dp, height = 20.dp),
    statMaxWidth = 48.dp,
    shimmerTextHeight = 20.dp,
    imageMedium = DpSize(width = 80.dp, height = 80.dp), //
    imageLarge = DpSize(width = 120.dp, height = 120.dp), //
    progressBarHeightSmall = 8.dp,
    iconBorderWidth = 2.dp,
    iconBorderWidthSmall = 1.dp,
)

internal val localDimension = staticCompositionLocalOf<Dimension> { throw IllegalStateException("No Dimension installed") }

val MaterialTheme.dimension: Dimension
    @Composable
    get() = localDimension.current