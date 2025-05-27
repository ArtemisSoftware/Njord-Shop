package com.artemissoftware.njordshop.core.presentation.ui.composables.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.njordshop.R
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.NjordShopTheme
import com.artemissoftware.njordshop.core.presentation.ui.composables.loading.LoadingProgress
import com.artemissoftware.njordshop.core.presentation.ui.composables.placeholder.PlaceHolderContent
import com.artemissoftware.njordshop.core.presentation.ui.models.ErrorData


@Composable
fun NSScaffold(
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    topBar: @Composable () -> Unit = {},
    error: ErrorData? = null,
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Scaffold(
            modifier = Modifier
                .then(modifier),
            topBar = topBar,
            content = {  innerPadding ->

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        content.invoke()
                    }
                }
            },
        )

        LoadingProgress(isLoading = isLoading)

        error?.let {
            PlaceHolderContent(
                icon = R.drawable.ic_error_image,
                message = it.message.asString(),
                onClick = it.onClick,
                buttonText = it.buttonText.asString(),
            )
        }
    }
}

@Composable
fun NSScaffoldDouble(
    contentLeft: @Composable () -> Unit,
    contentRight: @Composable () -> Unit,
    isLoading: Boolean = false,
    error: ErrorData? = null,
) {
    Scaffold(
        modifier = Modifier,
        content = { innerPadding ->
            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Box(modifier = Modifier.weight(0.5F)) {
                        contentLeft.invoke()
                    }
                    Box(modifier = Modifier.weight(0.5F)) {
                        contentRight.invoke()
                    }
                }


                LoadingProgress(isLoading = isLoading)

                error?.let {
                    PlaceHolderContent(
                        icon = R.drawable.ic_error_image,
                        message = it.message.asString(),
                        onClick = it.onClick,
                        buttonText = it.buttonText.asString(),
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun NSScaffoldPreview() {
    NjordShopTheme {
        val colors = listOf(Color.Blue, Color.Cyan, Color.Green, Color.Magenta, Color.Yellow)
        NSScaffold(
            content = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(count = 10) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .background(colors[it % colors.size]),
                        )
                    }
                }
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun NSScaffoldDoublePreview() {
    NjordShopTheme {
        val colors = listOf(Color.Blue, Color.Cyan, Color.Green, Color.Magenta, Color.Yellow)
        NSScaffoldDouble(
            contentRight = {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray))
            },
            contentLeft = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(count = 10) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .background(colors[it % colors.size]),
                        )
                    }
                }
            },
        )
    }
}