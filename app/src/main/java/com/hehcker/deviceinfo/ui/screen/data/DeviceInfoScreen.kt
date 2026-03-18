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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hehcker.deviceinfo.ui.component.header.device.DeviceHeader
import com.hehcker.deviceinfo.ui.component.list.InfoListItem
import com.hehcker.deviceinfo.ui.screen.data.viewModel.DeviceInfoViewModel
import com.hehcker.deviceinfo.ui.theme.CustomColors.listItemColors
import com.hehcker.deviceinfo.ui.theme.CustomColors.topBarColors

@Composable
fun DeviceInfoScreen(
    viewModel: DeviceInfoViewModel = viewModel()
) {
    val details = viewModel.uiItems
    val deviceInfo = viewModel.deviceInfo
    
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier
            .background(topBarColors.containerColor)
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxSize()
    ) {
        item {
            DeviceHeader(
                model = deviceInfo.model,
                marketingName = deviceInfo.marketingName,
                brandIconRes = deviceInfo.brandIconRes
            )
        }
        if (details.isNotEmpty()) {
            itemsIndexed(details) { index, item ->
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
                    index = index + 1 // because of 'card' above
                )
            }
        }
    }
}