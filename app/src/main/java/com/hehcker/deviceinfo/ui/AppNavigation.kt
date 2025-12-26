package com.hehcker.deviceinfo.ui

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme.motionScheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hehcker.deviceinfo.ui.screen.main.MainScreen
import com.hehcker.deviceinfo.ui.screen.settings.SettingsScreen

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val motionScheme = motionScheme
    NavHost(
        navController = navController,
        startDestination = "main",
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = motionScheme.defaultSpatialSpec()
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it / 4 },
                animationSpec = motionScheme.fastSpatialSpec()
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it / 4 },
                animationSpec = motionScheme.defaultSpatialSpec()
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = motionScheme.fastSpatialSpec()
            )
        },
    ) {
        composable("main") {
            MainScreen(navController = navController)
        }
        composable("settings") {
            SettingsScreen(navController = navController)
        }
    }
}