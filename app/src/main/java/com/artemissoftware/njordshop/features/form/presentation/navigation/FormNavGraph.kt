package com.artemissoftware.njordshop.features.form.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.artemissoftware.njordshop.features.form.presentation.validation.ValidationScreen

fun NavGraphBuilder.formNavGraph(
    navController: NavHostController,
) {

    composable<FormRoute.Validation> {
        ValidationScreen()
    }
}