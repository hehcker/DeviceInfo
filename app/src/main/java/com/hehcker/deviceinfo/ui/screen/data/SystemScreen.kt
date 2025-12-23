package com.hehcker.deviceinfo.ui.screen.data

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hehcker.deviceinfo.R
import com.hehcker.deviceinfo.ui.component.InfoListItem
import com.hehcker.deviceinfo.ui.screen.data.viewModel.SystemViewModel
import com.hehcker.deviceinfo.ui.theme.CustomColors.listItemColors
import com.hehcker.deviceinfo.ui.theme.CustomColors.topBarColors
import com.hehcker.deviceinfo.ui.theme.WRShapeDefaults.middleListItemShape
import com.hehcker.deviceinfo.ui.theme.WRShapeDefaults.topListItemShape

@Composable
fun SystemScreen(
    viewModel: SystemViewModel = viewModel(),
) {
    val details = viewModel.uiItems
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier
            .background(topBarColors.containerColor)
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxSize()
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(topListItemShape)
                    .fillMaxWidth()
                    .background(listItemColors.containerColor)
                    .padding(16.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(
                            id = R.drawable.ic_android
                        ),
                        null,
                        modifier = Modifier
                            .height(48.dp)
                            .clip(middleListItemShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Android " + viewModel.systemInfo.androidVersion,
                        style = typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
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
                    index = index + 1
                )
            }
        }
    }
}