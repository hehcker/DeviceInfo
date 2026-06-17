package com.hehcker.deviceinfo.ui.navigation

sealed interface Screen {
    data object Main : Screen
    data object Settings : Screen
    data object DeviceInfo : Screen
    data object SystemInfo : Screen
    data object DisplayInfo : Screen
    data object Sample : Screen
}