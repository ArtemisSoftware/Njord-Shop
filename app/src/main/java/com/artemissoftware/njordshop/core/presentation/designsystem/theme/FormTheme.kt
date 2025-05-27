@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.artemissoftware.njordshop.core.presentation.designsystem.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val FormDarkColorScheme = darkColorScheme(
    primary = OrangeSecondary,
    onPrimary = OrangeOnSecondary,
    primaryContainer = OrangeOnPrimaryContainer,
    onPrimaryContainer = OrangePrimaryContainer,

    secondary = OrangePrimary,
    onSecondary = OrangeOnPrimary,
    secondaryContainer = OrangeSecondaryContainer,
    onSecondaryContainer = OrangeOnSecondaryContainer,

    background = OrangeBackgroundDark,
    onBackground = OrangeOnBackgroundDark,
    surface = OrangeSurfaceDark,
    onSurface = OrangeOnSurfaceDark,

    error = OrangeErrorDark,
    onError = OrangeOnErrorDark
)

private val FormLightColorScheme = lightColorScheme(
    primary = OrangePrimary,
    onPrimary = OrangeOnPrimary,
    primaryContainer = OrangePrimaryContainer,
    onPrimaryContainer = OrangeOnPrimaryContainer,

    secondary = OrangeSecondary,
    onSecondary = OrangeOnSecondary,
    secondaryContainer = OrangeSecondaryContainer,
    onSecondaryContainer = OrangeOnSecondaryContainer,

    background = OrangeBackgroundLight,
    onBackground = OrangeOnBackgroundLight,
    surface = OrangeSurfaceLight,
    onSurface = OrangeOnSurfaceLight,

    error = OrangeError,
    onError = OrangeOnError
)

@Composable
fun FormTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> FormDarkColorScheme
        else -> FormLightColorScheme
    }

    val context = LocalContext.current
    val windowClass = calculateWindowSizeClass(context as Activity)
    val isLandScape = windowClass.widthSizeClass != WindowWidthSizeClass.Compact //is land

    CompositionLocalProvider (
        localWindow provides if(isLandScape) landScape else portrait,
        localSpacing provides spacing,
        localDimension provides if(isLandScape) dimensionLandScape else dimensionPortrait,
        localFixedPalette provides fixedPalette,
        localShape provides shape,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}