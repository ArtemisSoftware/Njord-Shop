package com.artemissoftware.njordshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.FormTheme
import com.artemissoftware.njordshop.core.presentation.designsystem.theme.NjordShopTheme
import com.artemissoftware.njordshop.features.form.presentation.navigation.FormRoute
import com.artemissoftware.njordshop.navigation.RootNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FormTheme  {
                val navController = rememberNavController()

                RootNavGraph(
                    navController = navController,
                    startDestination = FormRoute.Validation,
                )
            }
        }
    }
}
