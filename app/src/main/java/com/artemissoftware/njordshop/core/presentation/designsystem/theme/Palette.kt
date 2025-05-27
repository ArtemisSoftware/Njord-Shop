package com.artemissoftware.njordshop.core.presentation.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class FixedPalette(
    val shimmer: List<Color>,
    val iconBackground: Color,
)


internal val fixedPalette = FixedPalette(
    shimmer = listOf(GrayShimmer1, GrayShimmer2, GrayShimmer1),
    iconBackground = LightGray,
)

internal val localFixedPalette = staticCompositionLocalOf<FixedPalette> { throw IllegalStateException("No Fixed Palette installed") }


val MaterialTheme.fixedPalette: FixedPalette
    @Composable
    get() = localFixedPalette.current