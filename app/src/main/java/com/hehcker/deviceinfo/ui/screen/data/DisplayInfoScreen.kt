@file:OptIn(ExperimentalMaterial3Api::class)

package com.hehcker.deviceinfo.ui.screen.data

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hehcker.deviceinfo.ui.component.header.DataScreenHeader
import com.hehcker.deviceinfo.ui.component.list.ClickableListItem
import com.hehcker.deviceinfo.ui.component.list.InfoListItem
import com.hehcker.deviceinfo.ui.component.sheet.ModalBottomListSheet
import com.hehcker.deviceinfo.ui.screen.data.viewModel.DisplayInfoViewModel
import com.hehcker.deviceinfo.ui.theme.CustomColors.listItemColors
import com.hehcker.deviceinfo.ui.theme.CustomColors.topBarColors

@Composable
fun DisplayInfoScreen(
    onBack: () -> Unit,
    viewModel: DisplayInfoViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory.getInstance(
            LocalContext.current.applicationContext as android.app.Application
        )
    ),
) {
    val details = viewModel.uiItems
    var showSheet by remember { mutableStateOf<String?>(null) }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    LaunchedEffect(Unit) { scrollBehavior.state.heightOffset = scrollBehavior.state.heightOffsetLimit }

    when (showSheet) {
        "HDR Types" -> {
            ModalBottomListSheet(
                title = "HDR Types",
                list = viewModel.displayInfo.hdrTypes,
                onDismiss = { showSheet = null }
            )
        }
        "Available Modes" -> {
            ModalBottomListSheet(
                title = "Available modes",
                list = viewModel.displayInfo.availableModes,
                onDismiss = { showSheet = null }
            )
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DataScreenHeader(
                title = "Display",
                onBack = onBack,
                scrollBehavior = scrollBehavior
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
                Spacer(modifier = Modifier.height(16.dp))
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
                            items = details.size,
                            index = index,
                            onClick = {
                                showSheet = item.label
                            }
                        )
                    } else {
                        InfoListItem(
                            label = item.label,
                            value = item.value,
                            colors = listItemColors,
                            items = details.size,
                            index = index
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}