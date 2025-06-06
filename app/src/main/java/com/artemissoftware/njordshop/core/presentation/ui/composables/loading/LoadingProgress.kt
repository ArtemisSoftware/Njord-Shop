package com.artemissoftware.njordshop.core.presentation.ui.composables.loading

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.NjordShopTheme
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.ThemePreviews
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.dimension

@Composable
fun LoadingProgress(isLoading: Boolean) {
    AnimatedVisibility(
        modifier = Modifier.fillMaxSize(),
        visible = isLoading
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(enabled = false, onClick = {})
                .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f)),
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(MaterialTheme.dimension.iconSizeBig),
            )
        }
    }
}

@ThemePreviews
@Composable
private fun LoadingProgressPreview() {
    NjordShopTheme {
        LoadingProgress(true)
    }
}