package com.hehcker.deviceinfo.ui.screen.data

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hehcker.deviceinfo.ui.component.header.system.AndroidVersionHeader
import com.hehcker.deviceinfo.ui.component.list.ClickableListItem
import com.hehcker.deviceinfo.ui.component.list.InfoListItem
import com.hehcker.deviceinfo.ui.component.sheet.ModalBottomListSheet
import com.hehcker.deviceinfo.ui.screen.data.viewModel.SystemInfoViewModel
import com.hehcker.deviceinfo.ui.theme.CustomColors.listItemColors
import com.hehcker.deviceinfo.ui.theme.CustomColors.topBarColors

@Composable
fun SystemInfoScreen(
    viewModel: SystemInfoViewModel = viewModel(),
) {
    val details = viewModel.uiItems
    var showSheet by remember { mutableStateOf<String?>(null) }

    when (showSheet) {
        "Device Features" -> {
            ModalBottomListSheet(
                title = "Device Features",
                list = viewModel.systemInfo.systemFeatures,
                onDismiss = { showSheet = null }
            )
        }
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier
            .background(topBarColors.containerColor)
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxSize()
    ) {
        item {
            AndroidVersionHeader(viewModel.systemInfo.androidVersion)
        }
        if (details.isNotEmpty()) {
            itemsIndexed(details) { index, item ->
                if (item.isClickable) {
                    ClickableListItem(
                        headlineContent = {
                            Text(
                                text = item.label,
                                style = typography.titleMedium
                            )
                        },
                        supportingContent = {
                            Text(
                                text = item.value,
                                style = typography.bodyMedium
                            )
                        },
                        items = details.size + 1,
                        index = index + 1,
                        onClick = {
                            showSheet = item.label
                        }
                    )
                } else {
                    InfoListItem(
                        headlineContent = {
                            Text(
                                text = item.label,
                                style = typography.titleMedium
                            )
                        },
                        supportingContent = {
                            Text(
                                text = item.value,
                                style = typography.bodyMedium
                            )
                        },
                        colors = listItemColors,
                        items = details.size + 1,
                        index = index + 1
                    )
                }
            }
        }
    }
}