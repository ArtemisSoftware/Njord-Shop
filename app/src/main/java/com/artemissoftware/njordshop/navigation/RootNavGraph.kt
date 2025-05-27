package com.artemissoftware.njordshop.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.artemissoftware.njordshop.features.form.presentation.navigation.formNavGraph
import com.artemissoftware.njordshop.features.products.presentation.navigation.productsNavGraph

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: Any,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        productsNavGraph(navController)

        formNavGraph(navController)
    }
}