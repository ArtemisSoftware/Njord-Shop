package com.artemissoftware.njordshop.core.presentation.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.artemissoftware.njordshop.R


private val wcxlLubriFamily: FontFamily = FontFamily(
    listOf(
        Font(R.font.wcxl_lubrifont_regular),
    ),
)


private val baseline = Typography()

val Typography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = wcxlLubriFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = wcxlLubriFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = wcxlLubriFamily),

    headlineLarge = baseline.headlineLarge.copy(fontFamily = wcxlLubriFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = wcxlLubriFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = wcxlLubriFamily),

    titleLarge = baseline.titleLarge.copy(fontFamily = wcxlLubriFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = wcxlLubriFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = wcxlLubriFamily),

    bodyLarge = baseline.bodyLarge.copy(fontFamily = wcxlLubriFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = wcxlLubriFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = wcxlLubriFamily),

    labelLarge = baseline.labelLarge.copy(fontFamily = wcxlLubriFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = wcxlLubriFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = wcxlLubriFamily)
)