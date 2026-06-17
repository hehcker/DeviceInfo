package com.hehcker.deviceinfo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import com.hehcker.deviceinfo.ui.navigation.AppNavigation
import com.hehcker.deviceinfo.ui.theme.DeviceInfoTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeviceInfoTheme {
                Scaffold(containerColor = MaterialTheme.colorScheme.surfaceContainer) {
                    AppNavigation()
                }
            }
        }
    }
}