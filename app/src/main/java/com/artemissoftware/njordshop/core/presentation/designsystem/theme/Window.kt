package com.artemissoftware.njordshop.core.presentation.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf


enum class WindowType{
    LANDSCAPE,
    PORTRAIT;
}

data class Orientation(
    val orientation: WindowType
){
    fun isLandScape() = orientation == WindowType.LANDSCAPE
}

internal val landScape = Orientation(WindowType.LANDSCAPE)
internal val portrait = Orientation(WindowType.PORTRAIT)

internal val localWindow = staticCompositionLocalOf<Orientation> { throw IllegalStateException("No window installed") }

val MaterialTheme.window: Orientation
    @Composable
    get() = localWindow.current