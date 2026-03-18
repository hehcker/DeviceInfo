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
import com.hehcker.deviceinfo.ui.component.list.ClickableListItem
import com.hehcker.deviceinfo.ui.component.list.InfoListItem
import com.hehcker.deviceinfo.ui.component.sheet.ModalBottomListSheet
import com.hehcker.deviceinfo.ui.screen.data.viewModel.DisplayInfoViewModel
import com.hehcker.deviceinfo.ui.theme.CustomColors.listItemColors
import com.hehcker.deviceinfo.ui.theme.CustomColors.topBarColors

@Composable
fun DisplayInfoScreen(
    viewModel: DisplayInfoViewModel = viewModel(),
) {
    val details = viewModel.uiItems
    var showHdrTypesSheet by remember { mutableStateOf(false) }
    var showModesSheet by remember { mutableStateOf(false) }

    if (showHdrTypesSheet) {
        ModalBottomListSheet(
            title = "HDR Types",
            list = viewModel.displayInfo.hdrTypes,
            onDismiss = { showHdrTypesSheet = false }
        )
    }
    if (showModesSheet) {
        ModalBottomListSheet(
            title = "Available modes",
            list = viewModel.displayInfo.availableModes,
            onDismiss = { showModesSheet = false }
        )
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier
            .background(topBarColors.containerColor)
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxSize()
    ) {
        if (details.isNotEmpty()) {
            itemsIndexed(details) { index, item ->
                if (item.label == "HDR Types") {
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
                        items = details.size,
                        index = index,
                        onClick = {
                            showHdrTypesSheet = true
                        }
                    )
                } else if (item.label == "Available Modes") {
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
                        items = details.size,
                        index = index,
                        onClick = {
                            showModesSheet = true
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
                        items = details.size,
                        index = index
                    )
                }
            }
        }
    }
}