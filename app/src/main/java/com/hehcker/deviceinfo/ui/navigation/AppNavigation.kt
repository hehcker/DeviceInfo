package com.hehcker.deviceinfo.ui.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.hehcker.deviceinfo.ui.screen.main.MainScreen
import com.hehcker.deviceinfo.ui.screen.settings.SettingsScreen
import com.hehcker.deviceinfo.ui.screen.data.DeviceInfoScreen
import com.hehcker.deviceinfo.ui.screen.data.SystemInfoScreen
import com.hehcker.deviceinfo.ui.screen.data.DisplayInfoScreen
import com.hehcker.deviceinfo.ui.screen.data.SampleScreen

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AppNavigation() {
    val backStack = remember { mutableStateListOf<Any>(Screen.Main) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = { it }
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { -it / 4 }
            )
        },
        popTransitionSpec = {
            slideInHorizontally(
                initialOffsetX = { -it / 4 }
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { it }
            )
        },
        predictivePopTransitionSpec = {
            slideInHorizontally(
                initialOffsetX = { -it / 4 }
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { it }
            )
        },
        entryProvider = entryProvider {
            entry<Screen.Main> {
                MainScreen(
                    onNavigate = { screen -> backStack.add(screen) }
                )
            }
            entry<Screen.Settings> {
                SettingsScreen(
                    onBack = { backStack.removeLastOrNull() }
                )
            }
            entry<Screen.DeviceInfo> {
                DeviceInfoScreen(
                    onBack = { backStack.removeLastOrNull() }
                )
            }
            entry<Screen.SystemInfo> {
                SystemInfoScreen(
                    onBack = { backStack.removeLastOrNull() }
                )
            }
            entry<Screen.DisplayInfo> {
                DisplayInfoScreen(
                    onBack = { backStack.removeLastOrNull() }
                )
            }
            entry<Screen.Sample> {
                SampleScreen(
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        }
    )
}