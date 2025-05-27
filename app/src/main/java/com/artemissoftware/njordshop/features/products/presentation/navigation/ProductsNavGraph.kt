package com.artemissoftware.njordshop.features.products.presentation.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.artemissoftware.njordshop.features.products.presentation.catalog.CatalogScreen
import com.artemissoftware.njordshop.features.products.presentation.detail.DetailScreen

fun NavGraphBuilder.productsNavGraph(
    navController: NavHostController,
) {

    composable<ProductsRoute.Catalog> {
        CatalogScreen(
            navigateToDetail = { navController.navigate(ProductsRoute.Detail(it)) },
        )
    }

    composable<ProductsRoute.Detail> {
        DetailScreen(
            onPopBack = {navController.popBackStack() }
        )
    }
}