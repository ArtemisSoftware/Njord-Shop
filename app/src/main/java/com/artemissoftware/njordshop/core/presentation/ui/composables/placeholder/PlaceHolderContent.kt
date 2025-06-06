package com.artemissoftware.njordshop.core.presentation.ui.composables.placeholder

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import com.artemissoftware.njordshop.R
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.NjordShopTheme
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.ThemePreviews
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.dimension
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.spacing


@Composable
fun PlaceHolderContent(
    message: String,
    @DrawableRes icon: Int = R.drawable.ic_place_holder,
    onClick: (() -> Unit)? = null,
    buttonText: String = "",
) {
    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.8f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "",
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(MaterialTheme.dimension.iconSizeBig)
                .alpha(alphaAnimation),
        )
        Text(
            modifier = Modifier
                .padding(MaterialTheme.spacing.spacing1_5)
                .alpha(alphaAnimation),
            text = message,
            style = MaterialTheme.typography.bodyMedium,
        )
        onClick?.let {
            Button(onClick = it) {
                Text(text = buttonText)
            }
        }
    }
}

@Composable
fun PlaceHolderNotice(
    onClick: (() -> Unit)? = null,
    buttonText: String = "",
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        onClick?.let {
            Button(onClick = it) {
                Text(text = buttonText)
            }
        }
    }
}

@ThemePreviews
@Composable
private fun PlaceHolderContentPreview() {
    NjordShopTheme {
        PlaceHolderContent(
            message = "Internet Unavailable.",
        )
    }
}

@ThemePreviews
@Composable
private fun PlaceHolderNoticePreview() {
    NjordShopTheme {
        PlaceHolderNotice()
    }
}