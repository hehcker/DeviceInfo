package com.hehcker.deviceinfo.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hehcker.deviceinfo.R
import com.hehcker.deviceinfo.data.DataRepository
import com.hehcker.deviceinfo.ui.component.header.main.InspectWarningHeader
import com.hehcker.deviceinfo.ui.component.icon.CircleIcon
import com.hehcker.deviceinfo.ui.component.list.InfoListItem
import com.hehcker.deviceinfo.ui.navigation.Screen
import com.hehcker.deviceinfo.ui.theme.CustomColors.topBarColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onNavigate: (Screen) -> Unit) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val importedDump by DataRepository.importedDump.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = if (importedDump != null) importedDump!!.deviceInfo.model else "Device Info",
                        style = typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = { onNavigate(Screen.Settings) }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_settings),
                            contentDescription = "Settings"
                        )
                    }
                },
                colors = topBarColors,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier
                .background(topBarColors.containerColor)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            contentPadding = innerPadding
        ) {
            if (importedDump != null) {
                item {
                    InspectWarningHeader()
                }
            }
            item {
                InfoListItem(
                    headlineContent = {
                        Text(
                            text = "Device",
                            style = typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    supportingContent = {
                        Text(
                            text = "Model, manufacturer, board, and hardware",
                            style = typography.bodyMedium
                        )
                    },
                    leadingContent = {
                        CircleIcon(
                            icon = ImageVector.vectorResource(R.drawable.ic_device),
                            contentDescription = "Phone",
                            colorIndex = 0
                        )
                    },
                    items = 5,
                    index = if (importedDump != null) 1 else 0,
                    onClick = { onNavigate(Screen.DeviceInfo) }
                )
            }
            item {
                InfoListItem(
                    headlineContent = {
                        Text(
                            text = "System",
                            style = typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    supportingContent = {
                        Text(
                            text = "Android version, build details",
                            style = typography.bodyMedium
                        )
                    },
                    leadingContent = {
                        CircleIcon(
                            icon = ImageVector.vectorResource(R.drawable.ic_system),
                            contentDescription = "System",
                            colorIndex = 1
                        )
                    },
                    items = 5,
                    index = 1,
                    onClick = { onNavigate(Screen.SystemInfo) }
                )
            }
            item {
                InfoListItem(
                    headlineContent = {
                        Text(
                            text = "Display",
                            style = typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    supportingContent = {
                        Text(
                            text = "Display size, modes, hdr and other features",
                            style = typography.bodyMedium
                        )
                    },
                    leadingContent = {
                        CircleIcon(
                            icon = ImageVector.vectorResource(R.drawable.ic_display),
                            contentDescription = "Display",
                            colorIndex = 2
                        )
                    },
                    items = 5,
                    index = 2,
                    onClick = { onNavigate(Screen.DisplayInfo) }
                )
            }
            item {
                InfoListItem(
                    headlineContent = {
                        Text(
                            text = "Battery",
                            style = typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    supportingContent = {
                        Text(
                            text = "Cycles, temperature, and health",
                            style = typography.bodyMedium
                        )
                    },
                    leadingContent = {
                        CircleIcon(
                            icon = ImageVector.vectorResource(R.drawable.ic_battery),
                            contentDescription = "Battery",
                            colorIndex = 3
                        )
                    },
                    items = 5,
                    index = 3,
                    onClick = { onNavigate(Screen.BatteryInfo) }
                )
            }
            item {
                InfoListItem(
                    headlineContent = {
                        Text(
                            text = "sample",
                            style = typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    supportingContent = {
                        Text(
                            text = "samples",
                            style = typography.bodyMedium
                        )
                    },
                    leadingContent = {
                        CircleIcon(
                            icon = ImageVector.vectorResource(R.drawable.ic_back),
                            contentDescription = "placeholder",
                            colorIndex = 9
                        )
                    },
                    items = 5,
                    index = 4,
                    onClick = { onNavigate(Screen.Sample) }
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}