@file:OptIn(ExperimentalMaterial3Api::class)

package com.hehcker.deviceinfo.ui.screen.settings

import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hehcker.deviceinfo.R
import com.hehcker.deviceinfo.data.DataRepository
import com.hehcker.deviceinfo.ui.component.list.InfoListItem
import com.hehcker.deviceinfo.ui.component.title.CategoryTitle
import com.hehcker.deviceinfo.ui.theme.CustomColors.topBarColors

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SettingsScreen(onBack: () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val context = LocalContext.current

    val exportLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/json")
    ) { uri ->
        uri?.let {
            val success = try {
                context.contentResolver.openOutputStream(it)?.use { stream ->
                    stream.write(DataRepository.generateDump(context).toByteArray())
                }
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }

            if (success) {
                Toast.makeText(context, "Exported successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Failed to export", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val importLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let {
            val jsonString = try {
                context.contentResolver.openInputStream(it)?.use { stream ->
                    stream.bufferedReader().readText()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }

            if (jsonString != null) {
                if (DataRepository.importDump(jsonString)) {
                    Toast.makeText(context, "Imported successfully", Toast.LENGTH_SHORT).show()
                    onBack()
                }
            } else {
                Toast.makeText(context, "Failed to import", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = "Settings",
                        style = typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        FilledTonalIconButton(
                            modifier = Modifier.padding(start = 8.dp),
                            onClick = onBack,
                            colors = IconButtonDefaults.filledTonalIconButtonColors(
                                containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                            )
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_back),
                                contentDescription = "Back"
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = topBarColors
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) { innerPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier
                .background(topBarColors.containerColor)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            contentPadding = innerPadding
        ) {
            item {
                CategoryTitle("System Information")
            }
            item {
                InfoListItem(
                    headlineContent = {
                        Text(
                            "Export",
                            style = typography.titleMedium
                        )
                    },
                    supportingContent = { Text("Save data as human readable json") },
                    onClick = { exportLauncher.launch("di_export_${Build.MODEL}_${System.currentTimeMillis()}.json") },
                    index = 0,
                    items = 2
                )
            }
            item {
                InfoListItem(
                    headlineContent = {
                        Text(
                            "Load",
                            style = typography.titleMedium
                        )
                    },
                    supportingContent = { Text("Import json file to inspect another system") },
                    onClick = { importLauncher.launch(arrayOf("application/json", "application/octet-stream")) },
                    index = 1,
                    items = 2
                )
            }
        }
    }
}